package com.tourismapp.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.tourismapp.backend.entity.location.City;

public interface CityDao extends BaseDao<City, Integer> {
	@Query("from City order by firstLetter")
	public List<City> findAllOrderByFirstLetter();

	@Query("from City where destinationStatus=true order by firstLetter")
	public List<City> findByDestinationStatus();
}
