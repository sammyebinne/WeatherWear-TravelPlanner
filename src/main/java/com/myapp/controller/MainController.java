package com.myapp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.myapp.model.WeatherData;
import com.myapp.service.ClothingRecommendationService;
import com.myapp.service.WeatherService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController {
	// our fxml input field
	@FXML
	private TextField cityTextField;
	// the output list for weather forecast
	@FXML
	private ListView<String> weatherListView;

	private WeatherService weatherService = new WeatherService();

	@FXML
	private void handleUserRequest() {
		// first we retrieve the text input and trim all whitespace
		String cityName = cityTextField.getText().trim();

		// user input validation
		if (cityName.isEmpty()) {
			displayAlert("Error", "City name cannot be empty");
			return;
		}

		try {
			// fetch the forecast Map using our Weather service
			Map<LocalDate, WeatherData> forecast = weatherService.getWeatherForecastMap(cityName);
			// we want to get our daily clothing recommendations in an ArrayList of Strings
			List<String> recommendations = ClothingRecommendationService.getRecommendations(forecast.values() // returns a collection
																																															.stream() // we convert it to a stream
																																															.toList()); // and then to a List
			
			// we then call our static method, displayWeatherData to transfer the data to weatherListView
			displayWeatherData(forecast, recommendations);
		} catch (Exception e) {
			displayAlert("Error", "Failed to fetch weather data. Please check the city name and try again."); // for the user to try a different input
			e.printStackTrace();
		}

	}

	private void displayWeatherData(Map<LocalDate, WeatherData> forecast, List<String> recommendations) {
		// clear previous data from weatherListView if any
		weatherListView.getItems().clear();
		//initialize a counter to loop through the recommendations
		int i = 0;
		// great opportunity to use the keySet() method for Maps
		for(LocalDate date : forecast.keySet()) {
			WeatherData data = forecast.get(date); // get the WeatherData objects (values from the forecast Map)
			
			// process the data into a string
			String listItems =  String.format("Date: %s, Temp: %.2f°C, High: %d°C, Low: %d°C, Humidity: %d%%, Wind: %.2f m/s, Description: %s\nRecommendation: %s", data.getDate(), data.getTemperature(), data.getHighTemp(), data.getLowTemp(), data.getHumidity(), data.getWindSpeed(), data.getDescription(), recommendations.get(i++));
						
			//pass the listItems into the weatherListView
			weatherListView.getItems().add(listItems);
		}
		
	}

	// helper displayAlert method
	private void displayAlert(String title, String message) {
		// create an error type alert
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.ERROR);

		a.setTitle(title);
		a.setHeaderText("Wrong Input");
		a.setContentText(message);
		a.showAndWait();

	}

}

// javafx Alert - https://www.geeksforgeeks.org/javafx-alert-with-examples/
