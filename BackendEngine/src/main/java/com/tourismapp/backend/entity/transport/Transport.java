package com.tourismapp.backend.entity.transport;

import java.util.Date;

import com.tourismapp.backend.entity.location.Location;

public abstract class Transport {
	public static enum Method {Air,Train,Bus,Subway,Taxi,Walk,Bicycle,Ship,Other};
	Method method;
	Location leaveLocation;
	Location arriveLocation;
	int durationMinutes;
	float price;
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Location getLeaveLocation() {
		return leaveLocation;
	}
	public void setLeaveLocation(Location leaveLocation) {
		this.leaveLocation = leaveLocation;
	}
	public Location getArriveLocation() {
		return arriveLocation;
	}
	public void setArriveLocation(Location arriveLocation) {
		this.arriveLocation = arriveLocation;
	}
	public int getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public abstract Date getArriveTime(Date leaveTime);
}
