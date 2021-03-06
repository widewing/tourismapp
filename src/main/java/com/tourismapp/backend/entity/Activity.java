package com.tourismapp.backend.entity;

import java.util.Date;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tourismapp.backend.entity.location.City;
import com.tourismapp.backend.entity.location.Scenery;

@Entity
@Table(name = "activity")
public class Activity {
	private List<City> cities;
	private String description;
	private Date endDate;
	private Integer id;
	private String imageUrl;
	private String name;
	private List<Scenery> sceneries;
	private Date startDate;

	private String tag;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "activity_id") }, inverseJoinColumns = { @JoinColumn(name = "city_id") })
	public List<City> getCities() {
		return cities;
	}

	@Column(name = "description", length = 255)
	public String getDescription() {
		return description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "endDate")
	public Date getEndDate() {
		return endDate;
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

	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "activity_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "scenery_id", referencedColumnName = "id") })
	public List<Scenery> getSceneries() {
		return sceneries;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "startDate")
	public Date getStartDate() {
		return startDate;
	}

	@Column(name = "tag", length = 20)
	public String getTag() {
		return tag;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSceneries(List<Scenery> sceneries) {
		this.sceneries = sceneries;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
