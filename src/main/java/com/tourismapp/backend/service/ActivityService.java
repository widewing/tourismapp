package com.tourismapp.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourismapp.backend.dao.ActivityDao;
import com.tourismapp.backend.dto.ActivityDto;
import com.tourismapp.backend.entity.Activity;

@Service
public class ActivityService {
	@Autowired
	private ActivityDao activityDao;

	@Transactional(readOnly = true)
	public Map<String, List<ActivityDto>> listAllActivitiesGroupByTag() {
		Map<String, List<ActivityDto>> result = new HashMap<String, List<ActivityDto>>();
		List<Activity> activities = activityDao.findByTodayActivities();
		for (Activity activity : activities) {
			List<ActivityDto> tagActivities = result.get(activity.getTag());
			if (null == tagActivities)
				tagActivities = new ArrayList<ActivityDto>();
			tagActivities.add(new ActivityDto(activity));
			result.put(activity.getTag(), tagActivities);
		}
		return result;
	}
}
