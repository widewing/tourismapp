package com.tourismapp.backend.controller;

import java.util.List;
import java.util.Map;

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
import com.tourismapp.backend.dto.location.SceneryDto;
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
	public List<Map<String, List<CityDto>>> listAllCitiesGroupByFirstLetter() {
		return cityService.ListAllCitiesGroupByFirstLetter();
	}

	@RequestMapping(value = "destinations", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Map<String, List<CityDto>>> listAllDestinationsGroupByFirstLetter() {
		return cityService.ListAllDesinationsGroupByFirstLetter();
	}

	@RequestMapping(value = "sceneries/{cityIds}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Map<String, List<SceneryDto>>> listAllDestinationsGroupByFirstLetter(@PathVariable List<Integer> cityIds) {
		return sceneryService.ListAllByCityIdsOrderByFirstLetter(cityIds);
	}
}