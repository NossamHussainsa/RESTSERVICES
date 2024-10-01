package com.nt.test.Entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {
	private String city;
	private String stateArea;
	private Integer pin;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateArea() {
		return stateArea;
	}
	public void setStateArea(String stateArea) {
		this.stateArea = stateArea;
	}
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
}
