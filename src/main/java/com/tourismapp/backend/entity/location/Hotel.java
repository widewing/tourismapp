package com.tourismapp.backend.entity.location;

import java.util.Map;

import javax.persistence.Entity;

@Entity
public class Hotel extends Location {
	public static enum RoomType {
		DELUXE, DOUBLE, KINGSIZE, OTHER, SINGLE, TRIPLE
	};

	Map<String, Float> prices;
	int rank;
	String room;

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
