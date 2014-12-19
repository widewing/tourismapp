package com.tourismapp.backend.entity.transport;

import java.util.Calendar;
import java.util.Date;

public class UnscheduledTransport extends Transport {
	@Override
	public Date getArriveTime(Date leaveTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(leaveTime);
		cal.add(Calendar.MINUTE, getDurationMinutes());
		return cal.getTime();
	}

}
