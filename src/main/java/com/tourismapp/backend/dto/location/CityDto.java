package com.tourismapp.backend.dto.location;

public class CityDto extends District {
	private String baiduCode;

	public CityDto() {
		super(Level.City);
	}

	@Override
	public String getBaiduCode() {
		return baiduCode;
	}
	
	@Override
	public void setBaiduCode(String baiduCode) {
		this.baiduCode = baiduCode;
	}

}
