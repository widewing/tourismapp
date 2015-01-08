package com.tourismapp.backend.entity.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City extends BaseLocationEntity {
	private String baiduCode;
	private String Country = "中国";
	private Boolean isDestination = false;
	private String province;

	@Override
	public boolean equals(Object e) {
		if (!(e instanceof City))
			return false;
		return id == null ? false : id.equals(((City) e).id);
	}

	@Column(name = "baidu_code", unique = true, nullable = false, length = 10)
	public String getBaiduCode() {
		return baiduCode;
	}

	@Column(name = "country", nullable = false, length = 20)
	public String getCountry() {
		return Country;
	}

	@Column(name = "is_destination", nullable = false)
	public Boolean getIsDestination() {
		return isDestination;
	}

	@Column(name = "province", nullable = false, length = 10)
	public String getProvince() {
		return province;
	}

	public void setBaiduCode(String baiduCode) {
		this.baiduCode = baiduCode;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public void setIsDestination(Boolean isDestination) {
		this.isDestination = isDestination;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
