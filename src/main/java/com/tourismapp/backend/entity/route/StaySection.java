package com.tourismapp.backend.entity.route;

import java.util.Calendar;
import java.util.Date;

import com.tourismapp.backend.entity.location.Location;

public class StaySection extends RouteSection {
	public StaySection(Location location,Date when,int durationMinutes,float price) {
		super(when, null,location,location,price);
		Calendar cal = Calendar.getInstance();
		cal.setTime(when);
		cal.add(Calendar.MINUTE, durationMinutes);
		setEndTime(cal.getTime());
	}
	public StaySection(Location location,Date startTime,Date endTime,float price) {
		super(startTime, endTime,location,location,price);
	}

	Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
