package com.tourismapp.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourismapp.backend.dao.SceneryDao;
import com.tourismapp.backend.dto.location.SceneryDto;
import com.tourismapp.backend.entity.location.Scenery;

@Service
public class SceneryService {
	@Autowired
	private SceneryDao sceneryDao;

	@Transactional(readOnly = true)
	public Map<Integer, List<SceneryDto>> ListAllByCityIdsOrderByFirstLetter(List<Integer> cityIds) {
		List<Scenery> sceneries = sceneryDao.findAllByCityIdsOrderByFirstLetter(cityIds);
		Map<Integer, List<SceneryDto>> result = new HashMap<Integer, List<SceneryDto>>();
		for (Scenery scenery : sceneries) {
			List<SceneryDto> tmpSceneryList = result.get(scenery.getCity().getId());
			if (tmpSceneryList == null) {
				tmpSceneryList = new ArrayList<SceneryDto>();
				result.put(scenery.getCity().getId(), tmpSceneryList);
			}
			tmpSceneryList.add(new SceneryDto(scenery));
		}
		return result;

	}

}
