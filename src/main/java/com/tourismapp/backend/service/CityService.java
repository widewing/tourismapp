package com.tourismapp.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourismapp.backend.dao.CityDao;
import com.tourismapp.backend.dto.location.CityDto;
import com.tourismapp.backend.entity.location.City;

@Service
public class CityService {
	@Autowired
	private CityDao cityDao;

	private Map<String, List<CityDto>> groupByFirstLetter(List<City> cities) {
		Map<String, List<CityDto>> result = new HashMap<String, List<CityDto>>();
		for (City city : cities) {
			List<CityDto> tmpCities = result.get(city.getFirstLetter());
			if (tmpCities == null)
				tmpCities = new ArrayList<CityDto>();
			tmpCities.add(new CityDto(city));
			result.put(city.getFirstLetter(), tmpCities);
		}
		return result;
	}

	public Map<String, List<CityDto>> ListAllCitiesGroupByFirstLetter() {
		List<City> cities = cityDao.findAllOrderByFirstLetter();
		return groupByFirstLetter(cities);
	}

	public Map<String, List<CityDto>> ListAllDesinationsGroupByFirstLetter() {
		List<City> cities = cityDao.findByDeinationCities();
		return groupByFirstLetter(cities);
	}
}
