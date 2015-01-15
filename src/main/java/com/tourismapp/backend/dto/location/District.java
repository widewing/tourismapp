package com.tourismapp.backend.dto.location;

public class District extends Location{
	public static enum Level {Country,Province,City,Town,Villege};
	protected Level level;
	
	public District(Level level){
		this.level = level;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
}
