package com.tourismapp.backend.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.tourismapp.backend.dto.location.CityDto;
import com.tourismapp.backend.dto.location.SceneryDto;
import com.tourismapp.backend.entity.Activity;

public class ActivityDto {
	private List<CityDto> cities;
	private String description;
	private Date endDate;
	private Integer id;
	private String imageUrl;
	private String name;
	private List<SceneryDto> sceneries;
	private Date startDate;
	private String tag;

	public ActivityDto() {
	}

	public ActivityDto(Activity entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public List<CityDto> getCities() {
		return cities;
	}

	public String getDescription() {
		return description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Integer getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getName() {
		return name;
	}

	public List<SceneryDto> getSceneries() {
		return sceneries;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getTag() {
		return tag;
	}

	public void setCities(List<CityDto> cities) {
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

	public void setSceneries(List<SceneryDto> sceneries) {
		this.sceneries = sceneries;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
