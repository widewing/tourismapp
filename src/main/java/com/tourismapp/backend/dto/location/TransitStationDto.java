package com.tourismapp.backend.dto.location;

public class TransitStationDto extends Location {
	public static enum Type {BusStation,AirPort,TrainStation,SubwayStation,Dock,Other};
	protected Type transitType;
	public TransitStationDto(Type transitType){
		this.transitType = transitType;
	}
	public Type getTransitType() {
		return transitType;
	}
	public void setTransitType(Type transitType) {
		this.transitType = transitType;
	}
	
}
