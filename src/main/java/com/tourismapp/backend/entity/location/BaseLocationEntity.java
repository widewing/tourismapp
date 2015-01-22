package com.tourismapp.backend.entity.location;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import com.tourismapp.backend.entity.Coord;

@MappedSuperclass
public class BaseLocationEntity {
	protected Coord coord;
	protected String description;
	private String firstLetter;
	protected Integer id;
	protected String imageUrl;
	protected double latitude;
	protected double longitude;
	protected String name;
	protected Set<String> tags = new HashSet<String>();
	protected String tagString;

	@Override
	public boolean equals(Object e) {
		if (!(e instanceof BaseLocationEntity))
			return false;
		return id == null ? false : id.equals(((BaseLocationEntity) e).id);
	}

	@Transient
	public Coord getCoord() {
		if (coord == null)
			coord = new Coord(latitude, longitude);
		return coord;
	}

	@Column(name = "description", length = 255)
	public String getDescription() {
		return description;
	}

	@Column(name = "first_letter", nullable = false, length = 1)
	public String getFirstLetter() {
		return firstLetter;
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
	public Set<String> getTags() {
		if (tags == null && !StringUtils.isEmpty(tagString))
			Collections.addAll(tags, tagString.split(","));
		return tags;
	}

	@Column(name = "tags", length = 255)
	public String getTagString() {
		return tagString;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
		latitude = coord.getLatitude();
		longitude = coord.getLongitude();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
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
		for (String string : tags)
			temp.append(string).append(',');
		tagString = temp.toString();
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}
}
