package com.tourismapp.backend.entity.location;

public class TransitStation extends Location {
	public static enum Type {BusStation,AirPort,TrainStation,SubwayStation,Wharf,Other};
	Type transitType;
	public Type getTransitType() {
		return transitType;
	}
	public void setTransitType(Type transitType) {
		this.transitType = transitType;
	}
	
}
