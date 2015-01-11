package com.tourismapp.backend.dto.route;

import java.util.List;
import java.util.concurrent.Callable;

public class CompoundSection extends RouteSection {
	List<RouteSection> sections;
	public CompoundSection(final List<RouteSection> sections) {
		super(
			sections.get(0).getStartTime(),
			sections.get(sections.size()-1).getEndTime(),
			sections.get(0).getStartLocation(),
			sections.get(sections.size()-1).getEndLocation(), new Callable<Float>(){
				@Override
				public Float call() {
					float price = 0;
					for(RouteSection section : sections){
						price += section.getPrice();
					}
					return price;
				}
			}.call());
		this.sections = sections;
	}
	public List<RouteSection> getSections(){
		return sections;
	}
}
