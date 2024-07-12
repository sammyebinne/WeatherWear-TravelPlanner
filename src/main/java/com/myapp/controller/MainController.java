package com.myapp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.myapp.model.ImageData;
import com.myapp.model.WeatherData;
import com.myapp.service.ClothingRecommendationService;
import com.myapp.service.WeatherService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {
	// our FXML input field
	@FXML
	private TextField cityTextField;
	
	// menu button
	@FXML
	private MenuButton dayMenuButton;
	
	
	// FXML img tags
	@FXML
	private ImageView weatherIcon;
	@FXML
	private ImageView temperatureIcon;
	@FXML
	private ImageView humidityIcon;
	@FXML
	private ImageView windIcon;
	
	//FXML label tags
	@FXML
	private Label temperatureLabel;
	@FXML
	private Label humidityLabel;
	@FXML
	private Label windLabel;
	@FXML
	private Label recommendationsLabel;
	@FXML
	private Label dateLabel;
	

	private WeatherService weatherService = new WeatherService();
	private Map<LocalDate, WeatherData> forecast;
    private List<String> recommendations;

    
    @FXML
    private void initialize() { // called as soon as the start method fires
    	// generate the resource location for our images
    	 String imgResourceTemperature = ImageData.getImage("temp");
         String imgResourceHumidity = ImageData.getImage("humidity");
         String imgResourceWind = ImageData.getImage("wind");
         
         // Set fixed icons for temperature, humidity, and wind
        temperatureIcon.setImage(new Image(getClass().getResource(imgResourceTemperature).toExternalForm()));
        humidityIcon.setImage(new Image(getClass().getResource(imgResourceHumidity).toExternalForm()));
        windIcon.setImage(new Image(getClass().getResource(imgResourceWind).toExternalForm()));
    }
    
	@FXML
	private void handleUserRequest() {
		// first we retrieve the text input and trim all whitespace
		String cityName = cityTextField.getText().trim();

		// if the string is empty, don't even bother making an API call
		if (cityName.isEmpty()) {
			displayAlert("Error", "City name cannot be empty");
			return;
		}

		try {
			// fetch the forecast Map using our Weather service
			forecast = weatherService.getWeatherForecastMap(cityName);
			// we want to get our daily clothing recommendations in an ArrayList of Strings
			recommendations = ClothingRecommendationService.getRecommendations(forecast.values() // returns a collection
																																									.stream() // we convert it to a stream
																																									.toList()); // and then to a List
			
			// we initialize the weather to today's weather
			setWeatherData(0);
			
		} catch (Exception e) {
			displayAlert("Error", "Failed to fetch weather data. Please check the city name and try again."); // for the user to try a different input
			e.printStackTrace();
		}

	}

	@FXML
	private void handleDaySelection(ActionEvent event) {
		// get the MenuItem obj that was selected
		MenuItem selected = (MenuItem) event.getSource();
		// find the text attribute of the item and separate after the space (Day 1 = ["Day", "1"]
		String[] itemText = selected.getText().split(" ");
		// get the string form of index, parse it into an int and subract one for day index
		int selectedIndex = Integer.parseInt(itemText[1]) - 1;
		// pass in the selected index to find day's weather
		setWeatherData(selectedIndex);
	}

	private void setWeatherData(int dayIndex) {
		// check if no API call has been made. Do nothing if so
		if (forecast == null || recommendations == null) return;
		
		// from the forecast map's values, select the corresponding day's conditions
		WeatherData weatherData = forecast.values().stream()
																							.toList()
																							.get(dayIndex);
		// select the recommendation for that day as well from the recommendations list
        String recommendation = recommendations.get(dayIndex);
        
        //set date
        dateLabel.setText("Weather conditions for " + weatherData.getDate());
        
        //get weather condition image for current day
        String imgResourceGeneral = ImageData.getImage(weatherData.getIconId());
        // convert it to a javafx image obj
        Image dayImage = new Image(getClass().getResource(imgResourceGeneral).toExternalForm());
        //set to the weatherIcon
        weatherIcon.setImage(dayImage);
		// set the rest of the attributes from the weatherData obj and recommendation list item
        temperatureLabel.setText(String.format("%.2f°C", weatherData.getTemperature()));
        humidityLabel.setText(String.format("%d%%", weatherData.getHumidity()));
        windLabel.setText(String.format("%.2f m/s", weatherData.getWindSpeed()));
        recommendationsLabel.setText(recommendation);
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
// javafx ActionEvent - https://www.tabnine.com/code/java/methods/javafx.event.ActionEvent/getSource
// https://docs.oracle.com/javase/8/javafx/api/javafx/event/ActionEvent.html
// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/MenuItem.html
