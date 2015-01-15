package com.tourismapp.backend.dto.location;

public class TransitStationDto extends Location {
	public static enum Type {BusStation,AirPort,TrainStation,SubwayStation,Wharf,Other};
	protected Type transitType;
	public Type getTransitType() {
		return transitType;
	}
	public void setTransitType(Type transitType) {
		this.transitType = transitType;
	}
	
}
