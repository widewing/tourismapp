package com.tourismapp.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tourismapp.backend.dto.ActivityDto;
import com.tourismapp.backend.dto.location.CityDto;
import com.tourismapp.backend.dto.location.HotelDto;
import com.tourismapp.backend.dto.location.HotelDto.RoomType;
import com.tourismapp.backend.dto.location.SceneryDto;
import com.tourismapp.backend.dto.route.HotelSection;
import com.tourismapp.backend.dto.route.RouteSection;
import com.tourismapp.backend.dto.route.StaySection;
import com.tourismapp.backend.dto.route.TransportSection;
import com.tourismapp.backend.dto.transport.Air;
import com.tourismapp.backend.dto.transport.TransportBase;
import com.tourismapp.backend.dto.transport.TransportBase.Method;
import com.tourismapp.backend.entity.Coord;
import com.tourismapp.backend.service.ActivityService;
import com.tourismapp.backend.service.CityService;
import com.tourismapp.backend.service.SceneryService;

@Controller
@RequestMapping("/api/")
public class RestAPIController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CityService cityService;
	@Autowired
	private SceneryService sceneryService;

	@RequestMapping(value = "activities", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, List<ActivityDto>> listAllActivitiesGroupByTag() {
		return activityService.listAllActivitiesGroupByTag();
	}

	@RequestMapping(value = "cities", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, List<CityDto>> listAllCitiesGroupByFirstLetter() {
		return cityService.ListAllCitiesGroupByFirstLetter();
	}

	@RequestMapping(value = "destinations", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, List<CityDto>> listAllDestinationsGroupByFirstLetter() {
		return cityService.ListAllDesinationsGroupByFirstLetter();
	}

	@RequestMapping(value = "sceneries/{cityIds}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<Integer, List<SceneryDto>> listAllDestinationsGroupByFirstLetter(@PathVariable List<Integer> cityIds) {
		return sceneryService.ListAllByCityIdsOrderByFirstLetter(cityIds);
	}

	@RequestMapping(value = "route/{cityIds}/{sceneryIds}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<List<RouteSection>> planRoutes(@PathVariable List<Integer> cityIds,
			@PathVariable List<Integer> sceneryIds) {
		List<RouteSection> day1 = new ArrayList<RouteSection>();
		List<RouteSection> day2 = new ArrayList<RouteSection>();
		List<RouteSection> day3 = new ArrayList<RouteSection>();
		List<List<RouteSection>> result = new ArrayList<List<RouteSection>>();
		TransportBase arriveTransport = new Air();
		TransportBase leaveTransport = new Air();
		CityDto hk = new CityDto();
		hk.setBaiduCode("789");
		Coord coord = new Coord(22.293586, 114.186124);
		hk.setCoord(coord);
		hk.setId(5);
		hk.setImageUrl("http://touris.sinaapp.com/static/img/city/hongkong.jpg");
		CityDto hz = new CityDto();
		hz.setBaiduCode("277");
		coord = new Coord(30.259244, 120.219375);
		hz.setCoord(coord);
		hz.setId(3);
		hz.setImageUrl("http://touris.sinaapp.com/static/img/city/hangzhou.jpg");
		arriveTransport.setArriveLocation(hk);
		arriveTransport.setLeaveLocation(hz);
		arriveTransport.setDurationMinutes(720);
		arriveTransport.setMethod(Method.Air);
		arriveTransport.setPrice(1000);
		day1.add(new TransportSection(arriveTransport, new DateTime(2015, 1, 1, 10, 0, 0, 0).toDate(), new DateTime(
				2015, 1, 1, 12, 0, 0, 0).toDate()));
		HotelDto day1Hotel = new HotelDto();
		day1Hotel.setCoord(coord);
		Map<RoomType, Float> prices = new HashMap<HotelDto.RoomType, Float>();
		prices.put(RoomType.KINGSIZE, 2500f);
		day1Hotel.setPrices(prices);
		day1.add(new HotelSection(day1Hotel, new DateTime(2015, 1, 1, 13, 0, 0, 0).toDate(), new DateTime(2015, 1, 1,
				14, 0, 0, 0).toDate(), RoomType.KINGSIZE));
		SceneryDto disney = new SceneryDto();
		coord = new Coord(22.316353, 114.055141);
		disney.setCoord(coord);
		disney.setBelongTo(hk);
		StaySection disneySection = new StaySection(disney, new DateTime(2015, 1, 1, 14, 0, 0, 0).toDate(),
				new DateTime(2015, 1, 1, 21, 0, 0, 0).toDate(), 700);
		day1.add(disneySection);
		day1.add(new HotelSection(day1Hotel, new DateTime(2015, 1, 1, 21, 0, 0, 0).toDate(), new DateTime(2015, 1, 2,
				9, 0, 0, 0).toDate(), RoomType.KINGSIZE));
		day2.add(new HotelSection(day1Hotel, new DateTime(2015, 1, 2, 9, 0, 0, 0).toDate(), new DateTime(2015, 1, 2,
				10, 0, 0, 0).toDate(), RoomType.KINGSIZE));
		SceneryDto dushalady = new SceneryDto();
		dushalady.setBelongTo(hk);
		coord = new Coord(22.27442, 114.161256);
		dushalady.setCoord(coord);
		StaySection dushaladySection = new StaySection(dushalady, new DateTime(2015, 1, 2, 10, 0, 0, 0).toDate(),
				new DateTime(2015, 1, 2, 14, 0, 0, 0).toDate(), 700);
		day2.add(dushaladySection);
		leaveTransport.setArriveLocation(hz);
		leaveTransport.setLeaveLocation(hk);
		leaveTransport.setDurationMinutes(720);
		leaveTransport.setMethod(Method.Air);
		leaveTransport.setPrice(1000);
		day2.add(new TransportSection(leaveTransport, new DateTime(2015, 1, 2, 15, 0, 0, 0).toDate(), new DateTime(
				2015, 1, 2, 19, 0, 0, 0).toDate()));
		result.add(day1);
		result.add(day2);
		return result;
	}
}
