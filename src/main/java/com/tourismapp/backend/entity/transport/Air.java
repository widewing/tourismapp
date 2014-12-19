package com.tourismapp.backend.entity.transport;

import com.tourismapp.backend.entity.location.TransitStation;

public class Air extends ScheduledTransport {
	protected TransitStation.Type stationType = TransitStation.Type.AirPort;
	public static enum Cabin {FirstClass,Business,Economy};
	Cabin cabin;
	
	public Cabin getCabin() {
		return cabin;
	}
	public void setCabin(Cabin cabin) {
		this.cabin = cabin;
	}
}
