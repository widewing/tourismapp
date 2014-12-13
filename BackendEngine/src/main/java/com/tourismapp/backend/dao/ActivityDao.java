package com.tourismapp.backend.dao;

import java.util.List;

import com.tourismapp.backend.entity.Activity;

public interface ActivityDao extends BaseDao<Activity, Integer> {
	public List<Activity> findAll();
}
