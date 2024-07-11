package com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import com.myapp.model.WeatherData;

public class ClothingRecommendationService {
	
	// we want to take in a List of the Map values returned from our weather service 
	// then we'll use simple if statements to generate a clothing recommendation for each day's forecast
	// we then return all the recommendations in a String List (arraylist)
	public static List<String> getRecommendations(List<WeatherData> weatherConditionsList){
		// empty list to pass in our recommendations while we loop through weatherConditionsList
		List <String> recommendations = new ArrayList<>();
		
		for (WeatherData weather : weatherConditionsList) {
			// let's handle temperature first
			if (weather.getTemperature() > 25) {
				recommendations.add("Pack some light clothing. It might get a little hot!");
			} else if(weather.getTemperature() > 15) {
				recommendations.add("A light jacket would do. Or a sweater.");
			} else {
				recommendations.add("Pack some warm clothing and stay warm out there!");
			}
			
			if(weather.getDescription().contains("rain")){
				recommendations.add("Take an umbrella or raincoat with you!");
			}
//			recommendations.add(weather.getSummary()); // not working as expected
		}
		
		return recommendations;
	}
}
