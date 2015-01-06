package com.tourismapp.backend.dto.route;

import java.util.Calendar;
import java.util.Date;

import com.tourismapp.backend.dto.location.Location;

public class StaySection extends RouteSection {
	Location location;

	public StaySection(Location location, Date startTime, Date endTime, float price) {
		super(startTime, endTime, location, location, price);
	}

	public StaySection(Location location, Date when, int durationMinutes, float price) {
		super(when, null, location, location, price);
		Calendar cal = Calendar.getInstance();
		cal.setTime(when);
		cal.add(Calendar.MINUTE, durationMinutes);
		setEndTime(cal.getTime());
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
