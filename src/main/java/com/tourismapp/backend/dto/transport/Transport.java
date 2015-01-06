package com.tourismapp.backend.dto.transport;

import java.util.Date;

import com.tourismapp.backend.dto.location.Location;

public abstract class Transport {
	public static enum Method {
		Air, Bicycle, Bus, Other, Ship, Subway, Taxi, Train, Walk
	};

	Location arriveLocation;
	int durationMinutes;
	Location leaveLocation;
	Method method;
	float price;

	public Location getArriveLocation() {
		return arriveLocation;
	}

	public abstract Date getArriveTime(Date leaveTime);

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public Location getLeaveLocation() {
		return leaveLocation;
	}

	public Method getMethod() {
		return method;
	}

	public float getPrice() {
		return price;
	}

	public void setArriveLocation(Location arriveLocation) {
		this.arriveLocation = arriveLocation;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public void setLeaveLocation(Location leaveLocation) {
		this.leaveLocation = leaveLocation;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
