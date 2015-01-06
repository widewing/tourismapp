package com.tourismapp.backend.dto.location;

public class DistrictDto extends Location{
	public static enum Level {Country,Province,City,Town,Villege};
	Level level;
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
}
