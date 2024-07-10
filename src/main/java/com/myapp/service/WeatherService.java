package com.myapp.service;

// we'll need to import the WeatherData model, HttpUtil, WeatherApiUtil and our json-simple
import com.myapp.model.WeatherData;
import com.myapp.utils.HttpUtil;
import com.myapp.utils.WeatherApiKeyUtil;
import org.json.JSONObject;

public class WeatherService {
	// calling openweather API key from WeatherApiKeyUtil
	String API_key = WeatherApiKeyUtil.getApiKey();

	private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

	// Method to get weather data for a given city and map it to the WeatherData model
	public WeatherData getWeatherData(String city) {
		// takes the string format specifier %s and replaces it with the city name and
		// the API_key
		String url = String.format(API_URL, city, API_key);

//		System.out.println(url); // used this to check if the url was properly processed

		// we store the string response from HttpUtil's fetchData method in a String
		// variable, response
		String response = HttpUtil.fetchData(url);

		System.out.println(response);

		// we then call tour helper method to parse the response data into the
		// WeatherData object
		return parseWeatherData(response);

	}

	

//	 Line 68-72 are strictly for testing purposes
	public static void main(String[] args) {
//		WeatherService test = new WeatherService();
//
//		test.getWeatherData("toronto");
	}

}

// documentation for json-simple -
// https://cliftonlabs.github.io/json-simple/target/apidocs/index.html 
// https://mkyong.com/java/json-simple-example-read-and-write-json/#read-json-from-file-using-jsonsimple
// https://alex-public-doc.s3.amazonaws.com/json_simple-1.1/index.html