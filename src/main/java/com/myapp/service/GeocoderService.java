package com.myapp.service;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.myapp.utils.HttpUtil;
import com.myapp.utils.WeatherApiKeyUtil;

public class GeocoderService {
	// API as copied from the website. Using format specifiers as placeholders for
	// city and API key
	private static final String geo_API_URL = "http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s";

	// we use the city input by the user to find the longitude and latitude which
	// we'll store in an array
	public double[] getCoordinates(String cityInput) {

		try {
			//including this in the try-catch block for the UnsupportedEncodingException
			String encodedCity = URLEncoder.encode(cityInput, "UTF-8");
			
			String url = String.format(geo_API_URL, encodedCity, WeatherApiKeyUtil.getApiKey());
			
			String response = HttpUtil.fetchData(url);
			
			// used to check what the response type is
			// System.out.println(response); // response is an array with an object as its
			// first element
			
			// store the array in a new JSONarray using our json-simple parser
			JSONArray jsonArr = new JSONArray(response);
			if (jsonArr.length() > 0) {
			// store first element of jsonArr in a JSONObject variable
			JSONObject jsonObject = jsonArr.getJSONObject(0);

			// store the coordinates in long variables
			double longitude = jsonObject.getDouble("lon");
			double latitude = jsonObject.getDouble("lat");

			// System.out.println(longitude + " " + latitude);

			// create a double array to store the coordinates
			double[] coords = { latitude, longitude };

//			System.out.println(Arrays.toString(coords));

			// return the coordinate array
			return coords;
			} else {
                throw new RuntimeException("No coordinates found for the city: " + cityInput);
            }
		} catch (Exception e) {
			throw new RuntimeException("Error occured while getting coordinates: ", e); // this should throw an error if
																						// something goes wrong
																						// somewhere
		}

//		return null;
	}

	// for testing purposes
//	public static void main(String[] args) {
//		new GeocoderService().getCoordinates("New york");
//	}

}

// API Doc - https://openweathermap.org/api/geocoding-api