package com.tourismapp.backend.dto.location;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tourismapp.backend.entity.location.City;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
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
