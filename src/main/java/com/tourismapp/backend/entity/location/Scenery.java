package com.tourismapp.backend.entity.location;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "scenery")
public class Scenery extends BaseLocationEntity {
	float closeTime;
	Collection<SceneryEntrance> entrances;
	float openTime;
	float price;
	int rank;
	int visitMinutes;

	public float getCloseTime() {
		return closeTime;
	}

	public Collection<SceneryEntrance> getEntrances() {
		return entrances;
	}

	public float getOpenTime() {
		return openTime;
	}

	public float getPrice() {
		return price;
	}

	public int getRank() {
		return rank;
	}

	public int getVisitMinutes() {
		return visitMinutes;
	}

	public void setCloseTime(float closeTime) {
		this.closeTime = closeTime;
	}

	public void setEntrances(Collection<SceneryEntrance> entrances) {
		this.entrances = entrances;
	}

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
