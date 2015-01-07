package com.tourismapp.backend.dto.location;

import org.springframework.beans.BeanUtils;

import com.tourismapp.backend.entity.location.City;

public class CityDto extends Location {
	private String baiduCode;
	private CityDto belongTo;

	public CityDto() {
		super();
	}

	public CityDto(City entity) {
		BeanUtils.copyProperties(entity, this);
	}

	@Override
	public boolean equals(Object e) {
		if (!(e instanceof CityDto))
			return false;
		return id == null ? false : id.equals(((CityDto) e).id);
	}

	@Override
	public String getBaiduCode() {
		return baiduCode;
	}

	@Override
	public CityDto getBelongTo() {
		return belongTo;
	}

	@Override
	public void setBaiduCode(String baiduCode) {
		this.baiduCode = baiduCode;
	}

	public void setBelongTo(CityDto belongTo) {
		this.belongTo = belongTo;
	}

}
