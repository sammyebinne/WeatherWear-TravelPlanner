package com.myapp.service;

// we'll need to import the WeatherData model, the HttpUtil and our json simple
import com.myapp.model.WeatherData;
import com.myapp.utils.HttpUtil;
import com.myapp.utils.WeatherApiKeyUtil;
import org.json.JSONObject;

public class WeatherService {
	// calling openweather API key from WeatherApiKeyUtil
	String API_key = WeatherApiKeyUtil.getApiKey();

	private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

	// this allows us get the data from the API into the format of the WeatherData
	// model
	// we may or may not build another overloaded method that takes in country
	// instead of city and does the same thing for the given Country's weather data
	public WeatherData getWeatherData(String city) {
		// takes the string format specifier %s and replaces it with the city name and
		// the API_key
		String url = String.format(API_URL, city, API_key);

		System.out.println(url);

		// we store the string response from HttpUtil's fetchData method in a String
		// variable, response
		String response = HttpUtil.fetchData(url);

		System.out.println(response);

		// we then call tour helper method to parse the response data into the
		// WeatherData object
		return parseWeatherData(response);

	}

	private WeatherData parseWeatherData(String response) {
		JSONObject jsonObject = new JSONObject(response);

		String city = jsonObject.getString("name");
		String country= jsonObject.getJSONObject("sys").getString("country");
		double temperature = jsonObject.getJSONObject("main").getDouble("temp");
		int highTemp = jsonObject.getJSONObject("main").getInt("temp_max");
		int lowTemp = jsonObject.getJSONObject("main").getInt("temp_min");
		int pressure =  jsonObject.getJSONObject("main").getInt("pressure");
		int humidity = jsonObject.getJSONObject("main").getInt("humidity");
		double windSpeed =  jsonObject.getJSONObject("wind").getDouble("speed") ;
		String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

		System.out.println(windSpeed + " " + country + " " +  city + " " +  description);
		return null;
	}

	public static void main(String[] args) {
		WeatherService test = new WeatherService();

		test.getWeatherData("calgary");

	}

}

// documentation for json-simple -
// https://cliftonlabs.github.io/json-simple/target/apidocs/index.html 
// https://mkyong.com/java/json-simple-example-read-and-write-json/#read-json-from-file-using-jsonsimple
// https://alex-public-doc.s3.amazonaws.com/json_simple-1.1/index.html