package com.tourismapp.backend.dto.location;

import java.util.Collection;

//@Entity
public class RestaurantDto extends Location {
	int estimatedCost;
	Collection<String> famousFoods;
	int rank;
	int style;

	public int getEstimatedCost() {
		return estimatedCost;
	}

	public Collection<String> getFamousFoods() {
		return famousFoods;
	}

	public int getRank() {
		return rank;
	}

	public int getStyle() {
		return style;
	}

	public void setEstimatedCost(int estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public void setFamousFoods(Collection<String> famousFoods) {
		this.famousFoods = famousFoods;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setStyle(int style) {
		this.style = style;
	}

}
