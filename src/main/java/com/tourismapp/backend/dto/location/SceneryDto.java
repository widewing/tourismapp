package com.tourismapp.backend.dto.location;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.tourismapp.backend.entity.location.Scenery;

public class SceneryDto extends Location {
	protected Float closeTime;
	protected Float openTime;
	protected Float price;
	protected Integer rank;
	protected Integer visitMinutes;

	public SceneryDto() {
		super();
	}

	public SceneryDto(Scenery entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public Float getCloseTime() {
		return closeTime;
	}

	public List<SceneryEntranceDto> getEntrances() {
		return null;
	}

	public Float getOpenTime() {
		return openTime;
	}

	public Float getPrice() {
		return price;
	}

	public Integer getRank() {
		return rank;
	}

	public Integer getVisitMinutes() {
		return visitMinutes;
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
