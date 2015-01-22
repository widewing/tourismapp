package com.tourismapp.backend.entity.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scenery")
public class Scenery extends BaseLocationEntity {
	private City city;
	private Float closeTime;
	private Float openTime;
	private Float price;
	private Integer rank;
	private Integer visitMinutes;

	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	public City getCity() {
		return city;
	}

	@Column(name = "close_time")
	public Float getCloseTime() {
		return closeTime;
	}

	@Column(name = "open_time")
	public Float getOpenTime() {
		return openTime;
	}

	@Column(name = "price")
	public Float getPrice() {
		return price;
	}

	@Column(name = "rank")
	public Integer getRank() {
		return rank;
	}

	@Column(name = "visit_minutes", nullable = false)
	public Integer getVisitMinutes() {
		return visitMinutes;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setCloseTime(Float closeTime) {
		this.closeTime = closeTime;
	}

	public void setOpenTime(Float openTime) {
		this.openTime = openTime;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public void setVisitMinutes(Integer visitMinutes) {
		this.visitMinutes = visitMinutes;
	}

}
