package com.tourismapp.backend.entity.location;

import java.util.Collection;

public class Restaurant extends Location {
	int rank;
	int estimatedCost;
	int style;
	Collection<String> famousFoods;
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(int estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public Collection<String> getFamousFoods() {
		return famousFoods;
	}
	public void setFamousFoods(Collection<String> famousFoods) {
		this.famousFoods = famousFoods;
	}
	
}
