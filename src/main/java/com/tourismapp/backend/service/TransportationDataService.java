package com.tourismapp.backend.service;

import java.util.Collection;
import java.util.Date;

import com.tourismapp.backend.dto.location.Location;
import com.tourismapp.backend.dto.transport.Transport;

public class TransportationDataService {
	public Collection<Transport> getAvailableTransports(Date startTime, Location leaveLocation,
			Location arriveLocation, Transport.Method[] methods) {
		return null;
	}
}
