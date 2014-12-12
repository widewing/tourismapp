package com.tourismapp.backend.entity.location;

import java.util.Collections;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import com.tourismapp.backend.entity.Coord;

@Entity
@Table(name = "Location")
public class Location {
	private String baiduCode;
	private Location belongTo;
	private Coord coord;
	private String description;
	private Integer id;
	private String imageUrl;
	private double latitude;
	private double longitude;
	private String name;
	private HashSet<String> tags;
	private String tagString;

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

	@ManyToOne
	@JoinColumn(name = "belong_to")
	public Location getBelongTo() {
		return belongTo;
	}

	@Transient
	public Coord getCoord() {
		if (coord == null) {
			coord = new Coord(latitude, longitude);
		}
		return coord;
	}

	@Column(name = "description", length = 255)
	public String getDescription() {
		return description;
	}

	@Transient
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
	public Integer getId() {
		return id;
	}

	@Column(name = "image_url", length = 255)
	public String getImageUrl() {
		return imageUrl;
	}

	@Column(name = "latitude", precision = 10, scale = 6)
	public double getLatitude() {
		return latitude;
	}

	@Column(name = "longitude", precision = 10, scale = 6)
	public double getLongitude() {
		return longitude;
	}

	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return name;
	}

	@Transient
	public HashSet<String> getTags() {
		if (tags == null && StringUtils.isEmpty(tagString)) {
			Collections.addAll(tags, tagString.split(","));
		}
		return tags;
	}

	@Column(name = "tags", length = 255)
	public String getTagString() {
		return tagString;
	}

	public void setBaiduCode(String baiduCode) {
		this.baiduCode = baiduCode;
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
		StringBuffer temp = new StringBuffer();
		for (String string : tags) {
			temp.append(string).append(',');
		}
		tagString = temp.toString();
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}
}
