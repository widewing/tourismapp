package com.tourismapp.backend.dto.transport;

import java.util.Date;

import com.tourismapp.backend.dto.location.DistrictDto;
import com.tourismapp.backend.dto.location.TransitStationDto;

public class ScheduledTransport extends Transport {
	Date arriveTime;
	Date leaveTime;
	protected TransitStationDto.Type stationType = TransitStationDto.Type.Other;

	public DistrictDto getArriveDistrict() {
		if (!(arriveLocation instanceof TransitStationDto))
			return null;
		TransitStationDto station = (TransitStationDto) arriveLocation;
		if (station.getTransitType() != stationType)
			return null;
		if (!(station.getBelongTo() instanceof DistrictDto))
			return null;
		return (DistrictDto) station.getBelongTo();
	}

	public Date getArriveTime() {
		return arriveTime;
	}

	@Override
	public Date getArriveTime(Date leaveTime) {
		if (leaveTime.before(getLeaveTime()))
			return null;
		return getArriveTime();
	}

	public DistrictDto getLeaveDistrict() {
		if (!(leaveLocation instanceof TransitStationDto))
			return null;
		TransitStationDto station = (TransitStationDto) leaveLocation;
		if (station.getTransitType() != stationType)
			return null;
		if (!(station.getBelongTo() instanceof DistrictDto))
			return null;
		return (DistrictDto) station.getBelongTo();
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public TransitStationDto.Type getStationType() {
		return stationType;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
}
