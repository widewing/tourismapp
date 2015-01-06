package com.tourismapp.backend.dto.route;

import java.util.Date;

import com.tourismapp.backend.dto.transport.ScheduledTransport;
import com.tourismapp.backend.dto.transport.Transport;

public class TransportSection extends RouteSection {
	Transport transport;

	public TransportSection(ScheduledTransport transport) {
		super(transport.getLeaveTime(), transport.getArriveTime(), transport.getLeaveLocation(), transport
				.getArriveLocation(), transport.getPrice());
		this.transport = transport;
		startLocation = transport.getLeaveLocation();
		endLocation = transport.getArriveLocation();
	}

	public TransportSection(Transport transport, Date startTime, Date endTime) {
		super(startTime, endTime, transport.getLeaveLocation(), transport.getArriveLocation(), transport.getPrice());
		this.transport = transport;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

}
