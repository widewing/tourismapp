package com.tourismapp.backend.service;

import java.util.Collection;
import java.util.Date;
import com.tourismapp.backend.entity.location.Location;
import com.tourismapp.backend.entity.transport.Transport;

public class TransportationDataService {
	public Collection<Transport> getAvailableTransports(Date startTime,Location leaveLocation,Location arriveLocation,Transport.Method[] methods){
		return null;
	}
}
