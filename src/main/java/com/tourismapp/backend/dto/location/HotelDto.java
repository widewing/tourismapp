package com.tourismapp.backend.dto.location;

import java.util.Map;

public class HotelDto extends Location {
	public static enum RoomType {
		DELUXE, DOUBLE, KINGSIZE, OTHER, SINGLE, TRIPLE
	};

	protected Map<RoomType, Float> prices;
	protected int rank;

	public Map<RoomType, Float> getPrices() {
		return prices;
	}

	public int getRank() {
		return rank;
	}

	public void setPrices(Map<RoomType, Float> prices) {
		this.prices = prices;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
