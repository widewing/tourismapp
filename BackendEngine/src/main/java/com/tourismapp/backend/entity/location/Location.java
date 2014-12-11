package com.tourismapp.backend.entity.location;

import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tourismapp.backend.entity.Coord;

@Entity
@Table(name = "Location")
public class Location {
	private String baiduCode;
	private Location belongTo;
	private Coord coord;
	private String description;
	private String id;
	private String imageUrl;
	private double latitude;
	private double longitude;
	private String name;
	private HashSet<String> tags;

	@Override
	public boolean equals(Object e) {
		if (!(e instanceof Location)) {
			return false;
		}
		return id == null ? false : id.equals(((Location) e).id);
	}

	@Column(name = "baidu_code", unique = true, nullable = false, length = 10)
	public String getBaiduCode() {
		return baiduCode;
	}

	@Column(name = "belong_to", unique = true, precision = 10, scale = 0)
	public Location getBelongTo() {
		return belongTo;
	}

	public Coord getCoord() {
		return coord;
	}

	@Column(name = "description", length = 255)
	public String getDescription() {
		return description;
	}

	public District getDistrict() {
		Location cur = this;
		while (true) {
			if (cur == null) {
				return null;
			}
			if (cur instanceof District) {
				return (District) cur;
			}
			cur = cur.belongTo;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	public String getId() {
		return id;
	}

	@Column(name = "image_url", length = 255)
	public String getImageUrl() {
		return imageUrl;
	}

	@Column(name = "latitude", length = 255)
	public double getLatitude() {
		return latitude;
	}

	@Column(name = "longitude", length = 255)
	public double getLongitude() {
		return longitude;
	}

	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public HashSet<String> getTags() {
		return tags;
	}

	public void setBaiduCode(String baiduCode) {
		this.baiduCode = baiduCode;
	}

	public void setBelongTo(Location belongTo) {
		this.belongTo = belongTo;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTags(HashSet<String> tags) {
		this.tags = tags;
	}
}
