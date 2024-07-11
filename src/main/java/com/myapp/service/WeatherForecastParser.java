package com.myapp.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.myapp.model.WeatherData;

public class WeatherForecastParser {

	// we want to store our weather data in date-WeatherData object key-value pairs
	public Map<LocalDate, WeatherData> parse(String response) {
		// first, we create a HashMap
		Map<LocalDate, WeatherData> weatherForecastMap = new LinkedHashMap<>();

		// we want to leverage the API's daily forecast response which returns an array
		try {
			// the response is an object
			JSONObject jsonObj = new JSONObject(response);
			// the daily forecasts come in an array
			JSONArray jsonArrDaily = jsonObj.getJSONArray("daily");

			// we want to loop through the "daily" array to get all 8 days' weather and
			// treat each before storing in map
			for (int i = 0; i < jsonArrDaily.length(); i++) {
				// each day's forecast stored in an object
				JSONObject dayForecastObj = jsonArrDaily.getJSONObject(i);

				// we get the epoch-format date from the object and process it to LocalDate number (UNIX Epoch format)
				// UNIX Epoch format represents the number of seconds since January 1, 1970 (see foot notes)
				long dateEpoch = dayForecastObj.getLong("dt");
				//we use the Instant class and other Java.time package classes to convert to date
				LocalDate date = Instant.ofEpochSecond(dateEpoch)
															.atZone(ZoneId.systemDefault())
															.toLocalDate();

				// then we extract the other data in our WeatherData model
				double temperature = dayForecastObj.getJSONObject("temp").getDouble("day");
				int highTemp = dayForecastObj.getJSONObject("temp").getInt("max");
				int lowTemp = dayForecastObj.getJSONObject("temp").getInt("min");
				int humidity = dayForecastObj.getInt("humidity");
				double windSpeed = dayForecastObj.getDouble("wind_speed");
				String description = dayForecastObj.getJSONArray("weather").getJSONObject(0).getString("description");

				// WeatherData object for day i
				WeatherData dayForecast = new WeatherData(date, temperature, highTemp, lowTemp, humidity, windSpeed,
						description);

				// we use the HashMap's put() method to store the key and value
				weatherForecastMap.put(date, dayForecast);
			}

		} catch (Exception e) {
			throw new RuntimeException("Something went wrong while parsing the weather forecast: ", e); // catch any runtime errors that occur while parsing
		}

		return weatherForecastMap;
	}

}

// Documentation for API - https://openweathermap.org/api/one-call-3#current
// Unix time https://en.wikipedia.org/wiki/Unix_time
// .ofEpochDay() - https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#ofEpochDay-long-
// Java Instant - https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html
// toLocalDate() - https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html
