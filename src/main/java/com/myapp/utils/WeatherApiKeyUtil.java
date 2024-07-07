package com.myapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//API key is hidden in a different file to prevent getting pushed to github
// this Util is there to help us separate the entire logic of retrieving the key 
public class WeatherApiKeyUtil {
	private static String apiKey;

	static {
		try (InputStream input = new FileInputStream("config.properties")) {
			Properties prop = new Properties();
			// Load the properties file
			prop.load(input);
			// Get the API key from the properties file
			apiKey = prop.getProperty("api.key");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load API key from config.properties", e);
		}
	}

	public static String getApiKey() {
		return apiKey;
	}

	public static void main(String[] args) {
		
		// we can print out to test if we're getting the API key successfully 
		System.out.println("API Key: " + getApiKey());
	}
}
