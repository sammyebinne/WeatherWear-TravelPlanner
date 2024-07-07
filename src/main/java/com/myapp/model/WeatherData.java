package com.myapp.model;

// here we will populate all of the data fetched from the weather API
public class WeatherData {
	
	// the below are instance variables representing the different data we get back from our fetch call
	private String city;
	private String country;
	private double temperature;
	private int highTemp;
	private int lowTemp;
	private int pressure;
	private int humidity;
	private double windSpeed;
	private String description;

	// we then create a parameterized constructor
	public WeatherData(String city, String country, double temperature, int highTemp, int lowTemp, int pressure,
			int humidity, double windSpeed, String description) {
		this.city = city;
		this.country = country;
		this.temperature = temperature;
		this.highTemp = highTemp;
		this.lowTemp = lowTemp;
		this.pressure = pressure;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.description = description;
	}
	
	//
}
