package com.tourismapp.backend.dto.route;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tourismapp.backend.dto.location.Location;

public class RouteSection {
	Location endLocation;
	Date endTime;
	float price;
	RouteInfo routeInfo;
	Location startLocation;
	Date startTime;

	public RouteSection(Date startTime, Date endTime, Location startLocation, Location endLocation, float price) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.price = price;
	}

	public Location getEndLocation() {
		return endLocation;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public float getPrice() {
		return price;
	}

	public RouteInfo getRouteInfo() {
		return routeInfo;
	}

	public Location getStartLocation() {
		return startLocation;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
