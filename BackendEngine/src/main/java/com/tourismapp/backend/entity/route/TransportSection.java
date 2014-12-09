package com.tourismapp.backend.entity.route;

import java.util.Date;

import com.tourismapp.backend.entity.transport.ScheduledTransport;
import com.tourismapp.backend.entity.transport.Transport;

public class TransportSection extends RouteSection {
	Transport transport;
	
	public TransportSection(Transport transport,Date startTime,Date endTime) {
		super(startTime,endTime,transport.getLeaveLocation(),transport.getArriveLocation(),transport.getPrice());
		this.transport = transport;
	}
	public TransportSection(ScheduledTransport transport) {
		super(transport.getLeaveTime(),transport.getArriveTime(),transport.getLeaveLocation(),transport.getArriveLocation(),transport.getPrice());
		this.transport = transport;
		startLocation = transport.getLeaveLocation();
		endLocation = transport.getArriveLocation();
	}
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
}
