package com.tourismapp.backend.entity.route;

import java.util.Date;

import com.tourismapp.backend.entity.location.Hotel;

public class HotelSection extends StaySection{

	public HotelSection(Hotel hotel, Date startTime, Date endTime,
			int roomType) {
		super(hotel, startTime, endTime, hotel.getPrices().get(roomType));
	}

}
