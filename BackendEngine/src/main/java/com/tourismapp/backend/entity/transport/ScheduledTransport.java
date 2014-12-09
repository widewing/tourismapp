package com.tourismapp.backend.entity.transport;

import java.util.Date;

import com.tourismapp.backend.entity.location.District;
import com.tourismapp.backend.entity.location.TransitStation;

public class ScheduledTransport extends Transport {
	protected TransitStation.Type stationType = TransitStation.Type.Other;
	Date leaveTime;
	Date arriveTime;
	public TransitStation.Type getStationType() {
		return stationType;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public District getLeaveDistrict(){
		if(!(leaveLocation instanceof TransitStation))
			return null;
		TransitStation station = (TransitStation)leaveLocation;
		if(station.getTransitType()!=stationType)
			return null;
		if(!(station.getBelongTo() instanceof District))
			return null;
		return (District)station.getBelongTo();
	}
	public District getArriveDistrict(){
		if(!(arriveLocation instanceof TransitStation))
			return null;
		TransitStation station = (TransitStation)arriveLocation;
		if(station.getTransitType()!=stationType)
			return null;
		if(!(station.getBelongTo() instanceof District))
			return null;
		return (District)station.getBelongTo();
	}
	@Override
	public Date getArriveTime(Date leaveTime) {
		if(leaveTime.before(getLeaveTime()))
			return null;
		return getArriveTime();
	}
}
