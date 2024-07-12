package com.myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) { //define the method that runs when the start method is called
		// set up the entry node to the scene graph
		Parent root;
		try {
			// set up other children nodes 
			root = FXMLLoader.load(getClass().getResource("/com/myapp/views/WeatherWear.fxml"));
			// create a Scene and append the entry node to it
			Scene scene = new Scene(root);
			// provide the content for the stage
			scene.getStylesheets().add(getClass().getResource("/com/myapp/views/styles.css").toExternalForm());
			// set the scene on the stage
			primaryStage.setScene(scene);
			primaryStage.setTitle("Weather App");
			// show the stage
			primaryStage.show();
		} catch (Exception e) { // handle exceptions from loading fxml, initializing UI or triggering event handlers
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}