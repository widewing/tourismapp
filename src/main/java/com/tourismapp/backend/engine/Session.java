package com.tourismapp.backend.engine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import com.tourismapp.backend.dto.location.District;
import com.tourismapp.backend.dto.location.HotelDto;
import com.tourismapp.backend.dto.location.Location;
import com.tourismapp.backend.dto.location.RestaurantDto;
import com.tourismapp.backend.dto.location.SceneryDto;
import com.tourismapp.backend.dto.location.SceneryEntranceDto;
import com.tourismapp.backend.dto.route.RouteSection;
import com.tourismapp.backend.dto.route.StaySection;
import com.tourismapp.backend.dto.route.TransportSection;
import com.tourismapp.backend.dto.transport.ScheduledTransport;
import com.tourismapp.backend.dto.transport.Transport;
import com.tourismapp.backend.entity.Coord;
import com.tourismapp.backend.entity.location.Hotel;
import com.tourismapp.backend.entity.location.Restaurant;
import com.tourismapp.backend.entity.location.Scenery;
import com.tourismapp.backend.service.HotelDataService;
import com.tourismapp.backend.service.RestaurantDataService;
import com.tourismapp.backend.service.TransportationDataService;

public class Session {
	Date endDate;
	Location endLocation;
	Collection<SceneryDto> selectedSceneries;
	Date startDate;
	Location startLocation;
	Set<String> tags;
	float visitTimeCoefficient;
	Collection<Restaurant> wantedRestaurants;

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

	public void setSelectedSceneries(Collection<SceneryDto> selectedSceneries) {
		this.selectedSceneries = selectedSceneries;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public void setVisitTimeCoefficient(float visitTimeCoefficient) {
		this.visitTimeCoefficient = visitTimeCoefficient;
	}
	
	public void setWantedRestaurants(Collection<Restaurant> wantedRestaurants) {
		this.wantedRestaurants = wantedRestaurants;
	}

	RestaurantDataService restaurantData = new RestaurantDataService();
	HotelDataService hotelData = new HotelDataService();
	TransportationDataService transportation = new TransportationDataService();
	HotelDto lastNightHotel;

	//安排一个城市中的行程
	private List<RouteSection> arrangeForDistrict(District district, Collection<SceneryDto> sceneries,
			Location startLocation, Date startTime) {
		Collection<SceneryDto> remained = new HashSet<SceneryDto>(sceneries);
		LinkedList<RouteSection> routes = new LinkedList<RouteSection>();
		boolean haveLunch = false, haveDinner = false, haveBreakfast = false;
		while (!remained.isEmpty()) {
			Location currentLoc = routes.isEmpty() ? startLocation : routes.getLast().getEndLocation();
			Date currentTime = routes.isEmpty() ? startTime : routes.getLast().getEndTime();
			float hourInDay = currentTime.getTime() / 1000 % 86400 / 3600.0f;
			SceneryDto scenery = getNextScenery(hourInDay, currentLoc, remained);
			if (hourInDay < 10 && !haveBreakfast) {
				routes.addAll(arrangeRestaurant(currentLoc, currentTime, scenery));
				haveBreakfast = true;
				continue;
			}
			if (hourInDay > 11 && hourInDay < 13.5 && !haveLunch) {
				routes.addAll(arrangeRestaurant(currentLoc, currentTime, scenery));
				haveLunch = true;
				continue;
			}
			if (hourInDay > 16.5 && !haveDinner) {
				routes.addAll(arrangeRestaurant(currentLoc, currentTime, scenery));
				haveDinner = true;
				continue;
			}
			if (hourInDay > 17 && getNextScenery(hourInDay, currentLoc, remained) == null) {
				routes.addAll(arrangeHotel(currentTime, currentLoc));
				continue;
			}

			routes.addAll(arrangeVisit(currentLoc, currentTime, scenery, scenery.getVisitMinutes(), scenery.getPrice()));
		}
		return routes;
	}

	//根据当前位置和时间安排住宿，如果上一晚位置较近则安排上一晚酒店
	private List<RouteSection> arrangeHotel(Date currentTime, Location currentLoc) {
		if (lastNightHotel != null && lastNightHotel.getCoord().distance(currentLoc.getCoord()) < 20000)
			return arrangeVisit(currentLoc, currentTime, lastNightHotel, nextMorning(currentTime), lastNightHotel
					.getPrices().get(Hotel.RoomType.SINGLE.name()));
		HotelDto bestHotel = null;
		float bestHotelFitness = 0;
		for (HotelDto hotel : hotelData.getAvailableHotels(currentLoc.getCoord(), 3000, currentTime)) {
			float fitness = calcTagsFitness(tags, hotel.getTags());
			if (fitness > bestHotelFitness) {
				bestHotel = hotel;
				bestHotelFitness = fitness;
			}
		}
		lastNightHotel = bestHotel;
		return arrangeVisit(currentLoc, currentTime, bestHotel, nextMorning(currentTime),
				bestHotel.getPrices().get(Hotel.RoomType.SINGLE.name()));
	}

	//安排市内交通
	private List<TransportSection> arrangeInnerCityTransport(Location leaveLoc, Location arriveLoc, Date leaveTime) {
		Collection<Transport> availableTransports = transportation.getAvailableTransports(leaveTime, leaveLoc,
				arriveLoc, new Transport.Method[] { Transport.Method.Bus, Transport.Method.Taxi });
		return selectTransport(leaveLoc, arriveLoc, leaveTime, availableTransports);
	}

	//安排城际交通
	private List<TransportSection> arrangeInterCityTransport(Location leaveLoc, District arriveLoc, Date leaveTime) {
		Collection<Transport> availableTransports = transportation.getAvailableTransports(leaveTime, leaveLoc,
				arriveLoc,
				new Transport.Method[] { Transport.Method.Air, Transport.Method.Train, Transport.Method.Bus });
		return selectTransport(leaveLoc, arriveLoc, leaveTime, availableTransports);
	}

	//安排吃饭地
	private List<RouteSection> arrangeRestaurant(Location currentLoc, Date currentTime, Location destination) {
		List<Coord> availCoords = new ArrayList<Coord>();
		if (currentLoc instanceof SceneryDto && ((SceneryDto) currentLoc).getEntrances() != null)
			for (SceneryEntranceDto e : ((SceneryDto) currentLoc).getEntrances())
				availCoords.add(e.getCoord());
		else
			availCoords.add(currentLoc.getCoord());
		if (destination instanceof SceneryDto && ((SceneryDto) destination).getEntrances() != null)
			for (SceneryEntranceDto e : ((SceneryDto) destination).getEntrances())
				availCoords.add(e.getCoord());
		else
			availCoords.add(destination.getCoord());
		RestaurantDto bestRestaurant = null;
		float bestRestaurantFitness = 0;
		for (Coord coord : availCoords)
			for (RestaurantDto restaurant : restaurantData.getAvailableRestaurants(coord, 500, currentTime)) {
				float fitness = calcTagsFitness(tags, restaurant.getTags());
				if (fitness > bestRestaurantFitness) {
					bestRestaurant = restaurant;
					bestRestaurantFitness = fitness;
				}
			}
		return arrangeVisit(currentLoc, currentTime, bestRestaurant, 40, bestRestaurant.getEstimatedCost());
	}

	//安排游览景点（给定结束时间）
	private List<RouteSection> arrangeVisit(Location currentLoc, Date currentTime, Location visitLoc, Date visitUntil,
			float destinationPrice) {
		LinkedList<RouteSection> routes = new LinkedList<RouteSection>();
		routes.addAll(arrangeInnerCityTransport(currentLoc, visitLoc, currentTime));
		routes.add(new StaySection(visitLoc, routes.getLast().getEndTime(), visitUntil, destinationPrice));
		return routes;
	}

	//安排游览景点（给定游览时间）
	private List<RouteSection> arrangeVisit(Location currentLoc, Date currentTime, Location visitLoc,
			int durationMinutes, float destinationPrice) {
		LinkedList<RouteSection> routes = new LinkedList<RouteSection>();
		routes.addAll(arrangeInnerCityTransport(currentLoc, visitLoc, currentTime));
		routes.add(new StaySection(visitLoc, routes.getLast().getEndTime(), durationMinutes, destinationPrice));
		return routes;
	}

	//将景点按城市划分
	private Map<District, Collection<SceneryDto>> calcDistricts() {
		Map<District, Collection<SceneryDto>> districts = new HashMap<District, Collection<SceneryDto>>();
		for (SceneryDto s : selectedSceneries) {
			District district = s.getDistrict();
			if (!districts.containsKey(district))
				districts.put(district, new HashSet<SceneryDto>());
			districts.get(district).add(s);
		}
		return districts;
	}

	//计算城市旅行顺序
	private List<District> calcDistrictVisitOrder(Map<District, Collection<SceneryDto>> groups) {
		// Map<District,Integer> visitDays =
		// calcEstimatedDaysForDistricts(groups);
		// TODO
		return new ArrayList<District>(groups.keySet());
	}

	//计算每个城市的预计停留时间
	private Map<District, Integer> calcEstimatedDaysForDistricts(Map<District, Collection<Scenery>> groups) {
		Map<District, Integer> result = new HashMap<District, Integer>();
		int estimatedTotalHours = 0;
		for (Entry<District, Collection<Scenery>> group : groups.entrySet()) {
			int hours = calcEstimatedHours(group.getValue());
			result.put(group.getKey(), hours);
			estimatedTotalHours += hours;
		}
		int totalDays = (int) ((endDate.getTime() - startDate.getTime()) / 86400);
		int estimatedTotalDays = 0;
		for (Entry<District, Integer> e : result.entrySet()) {
			int days = e.getValue() * 8 / estimatedTotalHours;
			days = days > 0 ? days : 1;
			result.put(e.getKey(), days);
			estimatedTotalDays += days;
		}
		Random random = new Random();
		while (estimatedTotalDays != totalDays) {
			List<District> districts = new ArrayList<District>(result.keySet());
			District district = districts.get(random.nextInt(districts.size()));
			if (result.get(district) <= 1 && estimatedTotalDays > totalDays)
				continue;
			result.put(district, result.get(district) + (estimatedTotalDays > totalDays ? -1 : 1));
		}
		return result;
	}

	//计算景点的预计游览时间
	private int calcEstimatedHours(Collection<Scenery> sceneries) {
		int minutes = 0;
		for (Scenery s : sceneries)
			minutes += s.getVisitMinutes();
		return (int) ((minutes * visitTimeCoefficient + 40 * sceneries.size()) / 60);
	}

	//计算tag的相合度
	private float calcTagsFitness(Set<String> tags1, Set<String> tags2) {
		// TODO
		return 0;
	}

	//获取所有可能的城际交通路线
	private Collection<ScheduledTransport> getAvailableTransports(Collection<District> districts, Date startTime, Date endTime) {
		// TODO
		return null;
	}

	//获取每个城市的预计酒店价格
	private Map<District, Map<Date, Integer>> getEstimatedHotelPrices() {
		// TODO
		return null;
	}

	//从当前点寻找合适的下一个游览景点，如不存在返回null
	private SceneryDto getNextScenery(float hourInDay, Location currentLoc, Collection<SceneryDto> sceneries) {
		SceneryDto nearestScenery = null;
		double nearestDistance = 0;
		for (SceneryDto scenery : sceneries) {
			if (scenery.getOpenTime() > hourInDay + 0.5)
				continue;
			if (scenery.getCloseTime() * 60 < hourInDay * 60 + scenery.getVisitMinutes() * 0.67)
				continue;
			if (scenery.getEntrances() == null) {
				double dist = currentLoc.getCoord().distance(scenery.getCoord());
				if (nearestScenery == null || nearestDistance > dist) {
					nearestDistance = dist;
					nearestScenery = scenery;
				}
			} else
				for (Location entrance : scenery.getEntrances()) {
					double dist = currentLoc.getCoord().distance(entrance.getCoord());
					if (nearestScenery == null || nearestDistance > dist) {
						nearestDistance = dist;
						nearestScenery = scenery;
					}
				}
		}
		return nearestScenery;
	}

	//主入口
	public List<RouteSection> makeTravelRoute() {
		Map<District, Collection<SceneryDto>> sceneryDistricts = calcDistricts();
		List<District> districts = calcDistrictVisitOrder(sceneryDistricts);
		LinkedList<RouteSection> routes = new LinkedList<RouteSection>();
		Location currentLocation = startLocation;
		Date currentTime = startDate;
		for (District district : districts) {
			if (!currentLocation.getDistrict().equals(district)) {
				routes.addAll(arrangeInterCityTransport(currentLocation, district, currentTime));
				currentLocation = routes.getLast().getEndLocation();
				currentTime = routes.getLast().getEndTime();
			}
			routes.addAll(arrangeForDistrict(district, sceneryDistricts.get(district), currentLocation, currentTime));
			currentLocation = routes.getLast().getEndLocation();
			currentTime = routes.getLast().getEndTime();
		}
		if (!currentLocation.getDistrict().equals(endLocation.getDistrict())) {
			routes.addAll(arrangeInterCityTransport(currentLocation, endLocation.getDistrict(), currentTime));
			currentLocation = routes.getLast().getEndLocation();
			currentTime = routes.getLast().getEndTime();
		}
		routes.addAll(arrangeInnerCityTransport(currentLocation, endLocation, currentTime));
		return routes;
	}

	//第二天出发时间
	private Date nextMorning(Date currentTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentTime);
		cal.add(Calendar.DATE, 1);
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.add(Calendar.MINUTE, 8 * 60);
		return cal.getTime();
	}

	//选择最合适的交通方式
	private List<TransportSection> selectTransport(Location leaveLoc, Location arriveLoc, Date leaveTime,
			Collection<Transport> availableTransports) {
		Transport bestTransport = null;
		Date bestTransportArriveTime = null;
		for (Transport transport : availableTransports) {
			Date arriveTime = transport.getArriveTime(leaveTime);
			if (arriveTime == null)
				continue;
			if (bestTransport == null || arriveTime.before(bestTransportArriveTime)) {
				bestTransport = transport;
				bestTransportArriveTime = arriveTime;
			}
		}
		TransportSection section = new TransportSection(bestTransport, leaveTime, bestTransportArriveTime);
		List<TransportSection> res = new ArrayList<TransportSection>();
		res.add(section);
		return res;
	}
}
