package com.tourismapp.backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourismapp.backend.dao.CityDao;
import com.tourismapp.backend.dto.location.CityDto;
import com.tourismapp.backend.entity.location.City;
import com.tourismapp.backend.utils.DtoUtils;

@Service
public class CityService {
	@Autowired
	private CityDao cityDao;

	@Transactional(readOnly = true)
	public List<Map<String, List<CityDto>>> ListAllCitiesGroupByFirstLetter() {
		List<City> cities = cityDao.findAllOrderByFirstLetter();
		return DtoUtils.cityDtoUtil.groupByFirstLetter(cities);
	}

	@Transactional(readOnly = true)
	public List<Map<String, List<CityDto>>> ListAllDesinationsGroupByFirstLetter() {
		List<City> cities = cityDao.findByDeinationCities();
		return DtoUtils.cityDtoUtil.groupByFirstLetter(cities);
	}
}
