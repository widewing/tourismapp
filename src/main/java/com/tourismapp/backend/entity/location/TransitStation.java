package com.tourismapp.backend.entity.location;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transport_station")
public class TransitStation extends BaseLocationEntity {
	public static enum Type {
		AirPort, BusStation, Dock, Other, SubwayStation, TrainStation
	};

	Type transitType;

	public Type getTransitType() {
		return transitType;
	}

	public void setTransitType(Type transitType) {
		this.transitType = transitType;
	}

}
