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

import com.tourismapp.backend.data.HotelData;
import com.tourismapp.backend.data.RestaurantData;
import com.tourismapp.backend.data.Transportation;
import com.tourismapp.backend.entity.Coord;
import com.tourismapp.backend.entity.location.District;
import com.tourismapp.backend.entity.location.Hotel;
import com.tourismapp.backend.entity.location.Location;
import com.tourismapp.backend.entity.location.Restaurant;
import com.tourismapp.backend.entity.location.Scenery;
import com.tourismapp.backend.entity.location.SceneryEntrance;
import com.tourismapp.backend.entity.route.RouteSection;
import com.tourismapp.backend.entity.route.StaySection;
import com.tourismapp.backend.entity.route.TransportSection;
import com.tourismapp.backend.entity.transport.ScheduledTransport;
import com.tourismapp.backend.entity.transport.Transport;

public class Session {
	Date endDate;
	Location endLocation;
	HotelData hotelData = new HotelData();
	Hotel lastNightHotel;
	RestaurantData restaurantData = new RestaurantData();
	List<Scenery> selectedSceneries;
	Date startDate;
	Location startLocation;
	HashSet<String> tags;
	Transportation transportation = new Transportation();
	float visitTimeCoefficient;

	List<Restaurant> wantedRestaurants;

	private List<RouteSection> arrangeForDistrict(District district, Collection<Scenery> sceneries,
			Location startLocation, Date startTime) {
		Collection<Scenery> remained = new HashSet<Scenery>(sceneries);
		LinkedList<RouteSection> routes = new LinkedList<RouteSection>();
		boolean haveLunch = false, haveDinner = false, haveBreakfast = false;
		while (!remained.isEmpty()) {
			Location currentLoc = routes.isEmpty() ? startLocation : routes.getLast().getEndLocation();
			Date currentTime = routes.isEmpty() ? startTime : routes.getLast().getEndTime();
			float hourInDay = currentTime.getTime() / 1000 % 86400 / 3600.0f;
			Scenery scenery = getNextScenery(hourInDay, currentLoc, remained);
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

	private List<RouteSection> arrangeHotel(Date currentTime, Location currentLoc) {
		if (lastNightHotel != null && lastNightHotel.getCoord().distance(currentLoc.getCoord()) < 20000) {
			return arrangeVisit(currentLoc, currentTime, lastNightHotel, nextMorning(currentTime), lastNightHotel
					.getPrices().get(Hotel.RoomType.SINGLE.name()));
		}
		Hotel bestHotel = null;
		float bestHotelFitness = 0;
		for (Hotel hotel : hotelData.getAvailableHotels(currentLoc.getCoord(), 3000, currentTime)) {
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

	private List<TransportSection> arrangeInnerCityTransport(Location leaveLoc, Location arriveLoc, Date leaveTime) {
		Collection<Transport> availableTransports = transportation.getAvailableTransports(leaveTime, leaveLoc,
				arriveLoc, new Transport.Method[] { Transport.Method.Bus, Transport.Method.Taxi });
		return selectTransport(leaveLoc, arriveLoc, leaveTime, availableTransports);
	}

	private List<TransportSection> arrangeInterCityTransport(Location leaveLoc, District arriveLoc, Date leaveTime) {
		Collection<Transport> availableTransports = transportation.getAvailableTransports(leaveTime, leaveLoc,
				arriveLoc,
				new Transport.Method[] { Transport.Method.Air, Transport.Method.Train, Transport.Method.Bus });
		return selectTransport(leaveLoc, arriveLoc, leaveTime, availableTransports);
	}

	private List<RouteSection> arrangeRestaurant(Location currentLoc, Date currentTime, Location destination) {
		List<Coord> availCoords = new ArrayList<Coord>();
		if (currentLoc instanceof Scenery && ((Scenery) currentLoc).getEntrances() != null) {
			for (SceneryEntrance e : ((Scenery) currentLoc).getEntrances()) {
				availCoords.add(e.getCoord());
			}
		} else {
			availCoords.add(currentLoc.getCoord());
		}
		if (destination instanceof Scenery && ((Scenery) destination).getEntrances() != null) {
			for (SceneryEntrance e : ((Scenery) destination).getEntrances()) {
				availCoords.add(e.getCoord());
			}
		} else {
			availCoords.add(destination.getCoord());
		}
		Restaurant bestRestaurant = null;
		float bestRestaurantFitness = 0;
		for (Coord coord : availCoords) {
			for (Restaurant restaurant : restaurantData.getAvailableRestaurants(coord, 500, currentTime)) {
				float fitness = calcTagsFitness(tags, restaurant.getTags());
				if (fitness > bestRestaurantFitness) {
					bestRestaurant = restaurant;
					bestRestaurantFitness = fitness;
				}
			}
		}
		return arrangeVisit(currentLoc, currentTime, bestRestaurant, 40, bestRestaurant.getEstimatedCost());
	}

	private List<RouteSection> arrangeVisit(Location currentLoc, Date currentTime, Location visitLoc, Date visitUntil,
			float destinationPrice) {
		LinkedList<RouteSection> routes = new LinkedList<RouteSection>();
		routes.addAll(arrangeInnerCityTransport(currentLoc, visitLoc, currentTime));
		routes.add(new StaySection(visitLoc, routes.getLast().getEndTime(), visitUntil, destinationPrice));
		return routes;
	}

	private List<RouteSection> arrangeVisit(Location currentLoc, Date currentTime, Location visitLoc,
			int durationMinutes, float destinationPrice) {
		LinkedList<RouteSection> routes = new LinkedList<RouteSection>();
		routes.addAll(arrangeInnerCityTransport(currentLoc, visitLoc, currentTime));
		routes.add(new StaySection(visitLoc, routes.getLast().getEndTime(), durationMinutes, destinationPrice));
		return routes;
	}

	private Map<District, Collection<Scenery>> calcDistricts() {
		Map<District, Collection<Scenery>> districts = new HashMap<District, Collection<Scenery>>();
		for (Scenery s : selectedSceneries) {
			District district = s.getDistrict();
			if (!districts.containsKey(district)) {
				districts.put(district, new HashSet<Scenery>());
			}
			districts.get(district).add(s);
		}
		return districts;
	}

	private List<District> calcDistrictVisitOrder(Map<District, Collection<Scenery>> groups) {
		// Map<District,Integer> visitDays =
		// calcEstimatedDaysForDistricts(groups);
		// TODO
		return new ArrayList<District>(groups.keySet());
	}

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
			if (result.get(district) <= 1 && estimatedTotalDays > totalDays) {
				continue;
			}
			result.put(district, result.get(district) + (estimatedTotalDays > totalDays ? -1 : 1));
		}
		return result;
	}

	private int calcEstimatedHours(Collection<Scenery> sceneries) {
		int minutes = 0;
		for (Scenery s : sceneries) {
			minutes += s.getVisitMinutes();
		}
		return (int) ((minutes * visitTimeCoefficient + 40 * sceneries.size()) / 60);
	}

	private float calcTagsFitness(Set tags1, Set tags2) {
		// TODO
		return 0;
	}

	private Collection<ScheduledTransport> getAvailableTransports(Collection<District> districts, Date startTime,
			Date endTime) {
		// TODO
		return null;
	}

	private Map<District, Map<Date, Integer>> getEstimatedHotelPrices() {
		// TODO
		return null;
	}

	private Scenery getNextScenery(float hourInDay, Location currentLoc, Collection<Scenery> sceneries) {
		Scenery nearestScenery = null;
		double nearestDistance = 0;
		for (Scenery scenery : sceneries) {
			if (scenery.getOpenTime() > hourInDay + 0.5) {
				continue;
			}
			if (scenery.getCloseTime() * 60 < hourInDay * 60 + scenery.getVisitMinutes() * 0.67) {
				continue;
			}
			if (scenery.getEntrances() == null) {
				double dist = currentLoc.getCoord().distance(scenery.getCoord());
				if (nearestScenery == null || nearestDistance > dist) {
					nearestDistance = dist;
					nearestScenery = scenery;
				}
			} else {
				for (Location entrance : scenery.getEntrances()) {
					double dist = currentLoc.getCoord().distance(entrance.getCoord());
					if (nearestScenery == null || nearestDistance > dist) {
						nearestDistance = dist;
						nearestScenery = scenery;
					}
				}
			}
		}
		return nearestScenery;
	}

	public List<RouteSection> makeTravelRoute() {
		Map<District, Collection<Scenery>> sceneryDistricts = calcDistricts();
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

	private List<TransportSection> selectTransport(Location leaveLoc, Location arriveLoc, Date leaveTime,
			Collection<Transport> availableTransports) {
		Transport bestTransport = null;
		Date bestTransportArriveTime = null;
		for (Transport transport : availableTransports) {
			Date arriveTime = transport.getArriveTime(leaveTime);
			if (arriveTime == null) {
				continue;
			}
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
