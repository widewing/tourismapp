package com.tourismapp.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tourismapp.backend.entity.location.Location;

@Entity
@Table(name = "Activity")
public class Activity {
	private List<Location> cities;
	private String description;
	private Integer id;
	private String imageUrlString;
	private String name;
	private List<Location> spots;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "activity_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "location_id", referencedColumnName = "id") })
	public List<Location> getCities() {
		return cities;
	}

	@Column(name = "description", length = 255)
	public String getDescription() {
		return description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	public Integer getId() {
		return id;
	}

	@Column(name = "image_url", length = 255)
	public String getImageUrlString() {
		return imageUrlString;
	}

	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "activity_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "location_id", referencedColumnName = "id") })
	public List<Location> getSpots() {
		return spots;
	}

	public void setCities(List<Location> cities) {
		this.cities = cities;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageUrlString(String imageUrlString) {
		this.imageUrlString = imageUrlString;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpots(List<Location> spots) {
		this.spots = spots;
	}

}
