package com.tourismapp.backend.dto.location;

import java.util.HashSet;
import java.util.Set;

import com.tourismapp.backend.entity.Coord;

public class Location {
	protected Location belongTo;
	protected Coord coord;
	protected String description;
	protected Integer id;
	protected String imageUrl;
	protected Double latitude;
	protected Double longitude;
	protected String name;
	protected Set<String> tags = new HashSet<String>();

	public Location() {
	}

	@Override
	public boolean equals(Object e) {
		if (!(e instanceof Location))
			return false;
		return id == null ? false : id.equals(((Location) e).id);
	}

	public Location getBelongTo() {
		return belongTo;
	}

	public Coord getCoord() {
		if (coord == null)
			coord = new Coord(latitude, longitude);
		return coord;
	}

	public String getDescription() {
		return description;
	}

	public District getDistrict() {
		Location cur = this;
		while (true) {
			if (cur == null)
				return null;
			if (cur instanceof District)
				return (District) cur;
			cur = cur.belongTo;
		}
	}

	public Integer getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setBelongTo(Location belongTo) {
		this.belongTo = belongTo;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
		latitude = coord.getLatitude();
		longitude = coord.getLongitude();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
}
