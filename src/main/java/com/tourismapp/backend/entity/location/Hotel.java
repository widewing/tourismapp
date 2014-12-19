package com.tourismapp.backend.entity.location;

import java.util.Map;

public class Hotel extends Location {
	public static enum RoomType {SINGLE,DOUBLE,TRIPLE,KINGSIZE,DELUXE,OTHER};
	int rank;
	Map<String,Float> prices;
	String room;
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Map<String, Float> getPrices() {
		return prices;
	}
	public void setPrices(Map<String, Float> prices) {
		this.prices = prices;
	}
}
