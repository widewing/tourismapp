package com.tourismapp.backend.entity.location;

import java.util.Collection;

public class Scenery extends Location {
	int rank;
	int visitMinutes;
	float openTime;
	float closeTime;
	float price;
	Collection<SceneryEntrance> entrances;

	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getVisitMinutes() {
		return visitMinutes;
	}
	public void setVisitMinutes(int visitMinutes) {
		this.visitMinutes = visitMinutes;
	}
	public Collection<SceneryEntrance> getEntrances() {
		return entrances;
	}
	public void setEntrances(Collection<SceneryEntrance> entrances) {
		this.entrances = entrances;
	}
	public float getOpenTime() {
		return openTime;
	}
	public void setOpenTime(float openTime) {
		this.openTime = openTime;
	}
	public float getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(float closeTime) {
		this.closeTime = closeTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
}
