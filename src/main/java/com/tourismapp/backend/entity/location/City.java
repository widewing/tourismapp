package com.tourismapp.backend.entity.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City extends BaseLocationEntity {
	private String baiduCode;
	private String Country = "中国";
	private Boolean destinationStatus = false;
	private String firstLetter;
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
	public Boolean getDestinationStatus() {
		return destinationStatus;
	}

	@Column(name = "first_letter", nullable = false, length = 1)
	public String getFirstLetter() {
		return firstLetter;
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

	public void setDestinationStatus(Boolean destinationStatus) {
		this.destinationStatus = destinationStatus;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
