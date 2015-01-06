package com.tourismapp.backend.entity.location;


public class TransitStation extends BaseLocationEntity {
	public static enum Type {
		AirPort, BusStation, Other, SubwayStation, TrainStation, Wharf
	};

	Type transitType;

	public Type getTransitType() {
		return transitType;
	}

	public void setTransitType(Type transitType) {
		this.transitType = transitType;
	}

}
