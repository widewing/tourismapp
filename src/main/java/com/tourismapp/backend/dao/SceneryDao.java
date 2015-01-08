package com.tourismapp.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tourismapp.backend.entity.location.Scenery;

public interface SceneryDao extends BaseDao<Scenery, Integer> {
	@Query("from Scenery where city.id in (:cityIds) order by firstLetter")
	public List<Scenery> findAllByCityIdsOrderByFirstLetter(@Param("cityIds") List<Integer> cityIds);

}
