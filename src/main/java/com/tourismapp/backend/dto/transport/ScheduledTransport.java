package com.tourismapp.backend.dto.transport;

import java.util.Date;

import com.tourismapp.backend.dto.location.District;
import com.tourismapp.backend.dto.location.TransitStationDto;

public class ScheduledTransport extends TransportBase {
	Date arriveTime;
	Date leaveTime;
	
	public District getArriveDistrict() {
		if (!(arriveLocation instanceof TransitStationDto))
			return null;
		TransitStationDto station = (TransitStationDto) arriveLocation;
		if (!(station.getBelongTo() instanceof District))
			return null;
		return (District) station.getBelongTo();
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

	public District getLeaveDistrict() {
		if (!(leaveLocation instanceof TransitStationDto))
			return null;
		TransitStationDto station = (TransitStationDto) leaveLocation;
		if (!(station.getBelongTo() instanceof District))
			return null;
		return (District) station.getBelongTo();
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
}
