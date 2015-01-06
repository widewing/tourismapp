package com.tourismapp.backend.entity.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "scenery")
public class Scenery extends BaseLocationEntity {
	private float closeTime;
	// private Collection<SceneryEntrance> entrances;
	private float openTime;
	private float price;
	private int rank;
	private int visitMinutes;

	@Column(name = "close_time")
	public float getCloseTime() {
		return closeTime;
	}

	// public Collection<SceneryEntrance> getEntrances() {
	// return entrances;
	// }
	@Column(name = "open_time")
	public float getOpenTime() {
		return openTime;
	}

	@Column(name = "price")
	public float getPrice() {
		return price;
	}

	@Column(name = "rank")
	public int getRank() {
		return rank;
	}

	public int getVisitMinutes() {
		return visitMinutes;
	}

	public void setCloseTime(float closeTime) {
		this.closeTime = closeTime;
	}

	// public void setEntrances(Collection<SceneryEntrance> entrances) {
	// this.entrances = entrances;
	// }

	public void setOpenTime(float openTime) {
		this.openTime = openTime;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setVisitMinutes(int visitMinutes) {
		this.visitMinutes = visitMinutes;
	}

}
