package com.tourismapp.backend.dto.route;

import java.util.Date;

import com.tourismapp.backend.dto.transport.ScheduledTransport;
import com.tourismapp.backend.dto.transport.TransportBase;

public class TransportSection extends RouteSection {
	TransportBase transportBase;

	public TransportSection(ScheduledTransport transport) {
		super(transport.getLeaveTime(), transport.getArriveTime(), transport.getLeaveLocation(), transport
				.getArriveLocation(), transport.getPrice());
		this.transportBase = transport;
		startLocation = transport.getLeaveLocation();
		endLocation = transport.getArriveLocation();
	}

	public TransportSection(TransportBase transportBase, Date startTime, Date endTime) {
		super(startTime, endTime, transportBase.getLeaveLocation(), transportBase.getArriveLocation(), transportBase.getPrice());
		this.transportBase = transportBase;
	}

	public TransportBase getTransport() {
		return transportBase;
	}

	public void setTransport(TransportBase transportBase) {
		this.transportBase = transportBase;
	}

}
