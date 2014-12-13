package com.tourismapp.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourismapp.backend.dao.ActivityDao;
import com.tourismapp.backend.entity.Activity;

@Service
public class ActivityService {
	@Autowired
	private ActivityDao activityDao;

	@Transactional(readOnly = true)
	public List<Activity> listAll() {
		return activityDao.findAll();
	}

}
