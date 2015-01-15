package com.tourismapp.backend.dto.location;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.tourismapp.backend.entity.location.Scenery;

public class SceneryDto extends Location {
	protected float closeTime;
	protected float openTime;
	protected float price;
	protected int rank;
	protected int visitMinutes;

	public SceneryDto() {
		super();
	}

	public SceneryDto(Scenery entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public float getCloseTime() {
		return closeTime;
	}

	public List<SceneryEntranceDto> getEntrances() {
		return null;
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
