package com.myapp.model;

import java.time.LocalDate;

// here we will populate all of the data fetched from the weather API
public class WeatherData {

	// we need an instance variable to store the dates (this would be the key in our
	// Map)
	private LocalDate date;

	// the below are instance variables representing the different data we get back
	// from our fetch call
	private double temperature;
	private int highTemp;
	private int lowTemp;
	private int humidity;
	private double windSpeed;
	private String summary;
	private String description;

	

	// we then create a parameterized constructor
	public WeatherData(LocalDate date, double temperature, int highTemp, int lowTemp, int humidity, double windSpeed,
			String summary, String description) {
		this.date = date;
		this.temperature = temperature;
		this.highTemp = highTemp;
		this.lowTemp = lowTemp;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.summary = summary;
		this.description = description;
	}

	// set up getters to retrieve private instance variables
	public LocalDate getDate() {
		return date;
	}

	public double getTemperature() {
		return temperature;
	}

	public int getHighTemp() {
		return highTemp;
	}

	public int getLowTemp() {
		return lowTemp;
	}

	public int getHumidity() {
		return humidity;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public String getSummary() {
		return summary;
	}
	
	public String getDescription() {
		return description;
	}
	
	// override the inherited string method to give us a readable format of the objects of this class
	@Override
    public String toString() {
        return String.format("Date: %s, Temp: %.2f°C, High: %d°C, Low: %d°C, Humidity: %d%%, Wind: %.2f m/s, Summary: %s, Description: %s",
                date, temperature, highTemp, lowTemp, humidity, windSpeed, summary, description);
    }

}

// java.time package - https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html
