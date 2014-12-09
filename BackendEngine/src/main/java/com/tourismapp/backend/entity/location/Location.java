package com.tourismapp.backend.entity.location;

import java.util.HashSet;

import com.tourismapp.backend.entity.Coord;

public class Location {
	String name;
	String description;
	String id;
	Coord coord;
	HashSet<String> tags;
	Location belongTo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Coord getCoord() {
		return coord;
	}
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	public HashSet<String> getTags() {
		return tags;
	}
	public void setTags(HashSet<String> tags) {
		this.tags = tags;
	}
	public Location getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(Location belongTo) {
		this.belongTo = belongTo;
	}
	public District getDistrict(){
		Location cur = this;
		while(true){
			if(cur==null)
				return null;
			if(cur instanceof District)
				return (District)cur;
			cur = cur.belongTo;
		}
	}
	@Override public boolean equals(Object e){
		if(!(e instanceof Location))
			return false;
		return id==null?false:id.equals(((Location)e).id);
	}
}
