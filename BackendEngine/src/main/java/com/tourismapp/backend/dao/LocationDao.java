package com.tourismapp.backend.dao;

import com.tourismapp.backend.entity.location.Location;

public interface LocationDao extends BaseDao<Location, Integer> {
	public Location findByBaiduCode(String baiduCode);

}
