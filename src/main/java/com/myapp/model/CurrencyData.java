package com.myapp.model;

public class CurrencyData {
	// we set instance variables for the currency type and the rate
	private String currency;
    private double rate;
    
    // set up parameterized constructor
    public CurrencyData(String currency, double rate) {
    	this.currency = currency;
    	this.rate = rate;
    }

    // set up getters to retrieve private instant variables
	public String getCurrency() {
		return currency;
	}

	public double getRate() {
		return rate;
	}
    
    
}
