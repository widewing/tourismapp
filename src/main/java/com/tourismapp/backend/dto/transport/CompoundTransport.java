package com.tourismapp.backend.dto.transport;

import java.util.List;

import com.tourismapp.backend.dto.route.TransportSection;

public class CompoundTransport extends ScheduledTransport {

	private List<TransportSection> transits;

	public List<TransportSection> getTransits() {
		return transits;
	}

	public void setTransits(List<TransportSection> transits) {
		this.transits = transits;
	}
	
}
