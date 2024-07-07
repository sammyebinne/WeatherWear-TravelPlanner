package com.myapp.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtil {
	// Static method to fetch data from the URL using a GET request
	// it takes in the url string and returns the JSON object as a string
	public static String fetchData(String urlString) {
		// we use a try catch block to handle possible runtime exceptions
		try {
			// we create a HttpClient instance using Java 11 HTTP Client API
			HttpClient client = HttpClient.newHttpClient();
			// build HTTP request object
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(urlString)) // set the URI to urlString
					.GET() // specify GET as the request type
					.build(); // build the object

			// we send the request and receive the response then convert the response body to a String
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// if statusCode for response is 200, we're successful with the request and can return it
			if (response.statusCode() == 200) {
				return response.body();
			} else { // else, we want to throw the runtime exception with a custom message about the statusCode
				throw new RuntimeException("GET request failed, response code: " + response.statusCode());
			}

		} catch (Exception e) { // the catch block then gives us the original thrown exception
			throw new RuntimeException("Error while making GET request", e);
		}
	}

}


// resources
// https://naveen-metta.medium.com/java-11-http-client-api-unleashing-the-power-of-modern-web-communications-d7c16753d982
// https://openjdk.org/groups/net/httpclient/recipes.html