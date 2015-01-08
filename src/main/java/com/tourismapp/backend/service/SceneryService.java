package com.tourismapp.backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tourismapp.backend.dao.SceneryDao;
import com.tourismapp.backend.dto.location.SceneryDto;
import com.tourismapp.backend.entity.location.Scenery;
import com.tourismapp.backend.utils.DtoUtils;

public class SceneryService {
	@Autowired
	private SceneryDao sceneryDao;

	public Map<String, List<SceneryDto>> ListAllByCityIdsOrderByFirstLetter(List<Integer> cityIds) {
		List<Scenery> sceneries = sceneryDao.findAllByCityIdsOrderByFirstLetter(cityIds);
		return DtoUtils.sceneryDtoUtil.groupByFirstLetter(sceneries);

	}

}
