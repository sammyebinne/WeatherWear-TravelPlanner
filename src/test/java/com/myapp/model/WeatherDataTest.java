package com.myapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class WeatherDataTest {

	@Test
	void testGetWeatherConditions() {
		LocalDate date = LocalDate.parse("2024-07-11");
		double temperature = 19.40;
		int highTemp = 25;
		int lowTemp = 18;
		int humidity = 87;
		double windSpeed = 4.92;
		String summary = "Expect a day of partly cloudy with rain";
		String description = "moderate rain";
		String iconId = "10d";

		WeatherData weather1 = new WeatherData(date, temperature, highTemp, lowTemp, humidity, windSpeed, summary,
				description, iconId);

		assertEquals(date, weather1.getDate());
		assertEquals(temperature, weather1.getTemperature());
		assertEquals(highTemp, weather1.getHighTemp());
		assertEquals(lowTemp, weather1.getLowTemp());
		assertEquals(humidity, weather1.getHumidity());
		assertEquals(windSpeed, weather1.getWindSpeed());
		assertEquals(summary, weather1.getSummary());
		assertEquals(description, weather1.getDescription());
		assertEquals(iconId, weather1.getIconId());

	}

}
