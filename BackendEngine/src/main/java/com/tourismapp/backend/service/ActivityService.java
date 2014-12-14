package com.tourismapp.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, List<Activity>> listAllActivitiesGroupByTag() {
		Map<String, List<Activity>> result = new HashMap<>();
		List<Activity> activities = activityDao.findByTodayActivities();
		for (Activity activity : activities) {
			List<Activity> tagActivities = result.get(activity.getTag());
			if (null == tagActivities) {
				tagActivities = new ArrayList<>();
			}
			tagActivities.add(activity);
			result.put(activity.getTag(), tagActivities);
		}
		return result;
	}
}
