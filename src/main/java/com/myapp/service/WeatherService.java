package com.myapp.service;

import java.time.LocalDate;
import java.util.Map;

// we'll need to import the WeatherData model, HttpUtil, WeatherApiUtil and our json-simple
import com.myapp.model.WeatherData;
import com.myapp.utils.HttpUtil;
import com.myapp.utils.WeatherApiKeyUtil;

public class WeatherService {
	// calling openweather API key from WeatherApiKeyUtil
	String API_key = WeatherApiKeyUtil.getApiKey();
	// from https://openweathermap.org/api/one-call-3
	private static final String API_URL = "https://api.openweathermap.org/data/3.0/onecall?lat=%f&lon=%f&exclude=current,minutely,hourly,alerts&appid=%s&units=metric";
	
	// we create instances of our geocoding service and weatherMap service
	private final GeocoderService geocoder = new GeocoderService(); // takes in a String input (city name) - returns [x, y]
	private final WeatherForecastParser forecastMapper = new WeatherForecastParser(); // takes in a response string - return HashMap date ={WeatherData}
	

	// Overloaded method to get weather data for a given city entered by user
	public Map <LocalDate, WeatherData> getWeatherForecastMap(String city) {
		// get the coordinates array and store in a double array variable
		double[] coordinates = geocoder.getCoordinates(city);
		double x = coordinates[0];
		double y = coordinates[1];
		
		// call the over loaded method that takes in two double digits
		return getWeatherForecastMap(x, y);
	}
	
	// Overloaded method to get weather data for a given coordinates either entered by user or called by other overloaded method
	public Map <LocalDate, WeatherData> getWeatherForecastMap(double x, double y) {
		// pass in the x & y coords as well as the API key to build the fetch url
		String url = String.format(API_URL, x, y, API_key);
		
		// use the HttpUtil to get the response
		String response = HttpUtil.fetchData(url);
		
		// parse the response into a HashMap using our WeatherForecastParser service
		Map <LocalDate, WeatherData> forecastMap = forecastMapper.parse(response) ;
		
		forecastMap.forEach((key, value) -> System.out.println(key + " " + value));
		
		return forecastMap;
	}
	

//	 Line 68-72 are strictly for testing purposes
	public static void main(String[] args) {
		WeatherService test = new WeatherService();

		test.getWeatherForecastMap("toronto");
	}

}

// documentation for json-simple -
// https://cliftonlabs.github.io/json-simple/target/apidocs/index.html 
// https://mkyong.com/java/json-simple-example-read-and-write-json/#read-json-from-file-using-jsonsimple
// https://alex-public-doc.s3.amazonaws.com/json_simple-1.1/index.html