package com.tourismapp.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tourismapp.backend.entity.Activity;
import com.tourismapp.backend.service.ActivityService;

@RestController
@RequestMapping("api/")
public class RestAPIController {
	@Autowired
	private ActivityService activityService;

	@RequestMapping(value = "activities", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, List<Activity>> listAllActivitiesGroupByTag() {
		return activityService.listAllActivitiesGroupByTag();
	}
}