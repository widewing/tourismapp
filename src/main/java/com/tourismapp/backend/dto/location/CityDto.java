package com.tourismapp.backend.dto.location;

import org.springframework.beans.BeanUtils;

import com.tourismapp.backend.entity.location.City;

public class CityDto extends District {
	private String baiduCode;

	public CityDto() {
		super(Level.City);
	}

	public CityDto(City entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public String getBaiduCode() {
		return baiduCode;
	}

	public void setBaiduCode(String baiduCode) {
		this.baiduCode = baiduCode;
	}

}
