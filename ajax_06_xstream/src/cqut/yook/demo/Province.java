package cqut.yook.demo;

import java.util.ArrayList;
import java.util.List;

public class Province {
	private String name; // Ê¡Ãû
	private List<City> cites = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCites() {
		return cites;
	}

	public void setCites(List<City> cites) {
		this.cites = cites;
	}

	public void addCity(City city) {
		cites.add(city);
	}
}
