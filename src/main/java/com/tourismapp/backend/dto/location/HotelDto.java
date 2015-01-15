package com.tourismapp.backend.dto.location;

import java.util.Map;

import javax.persistence.Entity;

@Entity
public class HotelDto extends Location {
	public static enum RoomType {
		DELUXE, DOUBLE, KINGSIZE, OTHER, SINGLE, TRIPLE
	};

	protected Map<String, Float> prices;
	protected int rank;

	public Map<String, Float> getPrices() {
		return prices;
	}

	public int getRank() {
		return rank;
	}

	public void setPrices(Map<String, Float> prices) {
		this.prices = prices;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
