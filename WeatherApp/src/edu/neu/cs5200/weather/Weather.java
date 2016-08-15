package edu.neu.cs5200.weather;

import java.util.List;

public class Weather {
	private int id;
	private String locationname;
	private String zip;
	private Condition currentConditions;
	private List<Condition> forcast;
	
	public Weather(int id, String locationname, String zip, Condition currentConditions, List<Condition> forcast) {
		super();
		this.id = id;
		this.locationname = locationname;
		this.zip = zip;
		this.currentConditions = currentConditions;
		this.forcast = forcast;
	}
	
	public Weather(String locationname, String zip, Condition currentConditions, List<Condition> forcast) {
		super();
		this.locationname = locationname;
		this.zip = zip;
		this.currentConditions = currentConditions;
		this.forcast = forcast;
	}
	
	

	public Weather(int id, String locationname, String zip) {
		super();
		this.id = id;
		this.locationname = locationname;
		this.zip = zip;
	}

	public Weather(String locationname, String zip) {
		super();
		this.locationname = locationname;
		this.zip = zip;
	}

	public Weather() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Condition getCurrentConditions() {
		return currentConditions;
	}
	public void setCurrentConditions(Condition currentConditions) {
		this.currentConditions = currentConditions;
	}
	public List<Condition> getForcast() {
		return forcast;
	}
	public void setForcast(List<Condition> forcast) {
		this.forcast = forcast;
	}

}
