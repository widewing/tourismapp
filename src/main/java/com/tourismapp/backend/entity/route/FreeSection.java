package com.tourismapp.backend.entity.route;

import java.util.Date;

import com.tourismapp.backend.entity.location.Location;

public class FreeSection extends RouteSection {

	public FreeSection(Date startTime, Date endTime, Location startLocation,
			Location endLocation) {
		super(startTime, endTime, startLocation, endLocation,0);
	}

}
