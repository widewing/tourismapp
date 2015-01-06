package com.tourismapp.backend.dto.route;

import java.util.Date;

import com.tourismapp.backend.dto.location.Location;

public class FreeSection extends RouteSection {

	public FreeSection(Date startTime, Date endTime, Location startLocation, Location endLocation) {
		super(startTime, endTime, startLocation, endLocation, 0);
	}

}
