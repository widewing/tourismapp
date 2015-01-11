package com.tourismapp.backend.dto.transport;

public class Air extends ScheduledTransport {
	public static enum Cabin {FirstClass,Business,Economy};
	Cabin cabin;
	
	public Cabin getCabin() {
		return cabin;
	}
	public void setCabin(Cabin cabin) {
		this.cabin = cabin;
	}
}
