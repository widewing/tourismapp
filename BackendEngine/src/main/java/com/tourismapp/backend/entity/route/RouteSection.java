package com.tourismapp.backend.entity.route;

import java.util.Date;

import com.tourismapp.backend.entity.location.Location;

public class RouteSection {
	RouteInfo routeInfo;
	Date startTime;
	Date endTime;
	Location startLocation;
	Location endLocation;
	float price;
	public RouteSection(Date startTime, Date endTime, Location startLocation,Location endLocation,float price) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.price = price;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Location getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}
	public Location getEndLocation() {
		return endLocation;
	}
	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public RouteInfo getRouteInfo() {
		return routeInfo;
	}
	
}
