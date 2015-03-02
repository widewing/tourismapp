package com.tourismapp.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tourismapp.backend.entity.location.Transport;

public interface TransportDao extends BaseDao<Transport, Integer> {
	@Query("from TransportBase where startTransitStation.id=(:startStationId) and endTransitStation.id=(:endStationId)")
	public List<Transport> findTransports(@Param("startStationId") Integer startStationId,
			@Param("endStationId") Integer endStationId);
}
