package com.nt.test.Embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public class MobileNumbers {
	private Long primaryNumber;
	private Long secondaryNumber;
	public Long getPrimaryNumber() {
		return primaryNumber;
	}
	public void setPrimaryNumber(Long primaryNumber) {
		this.primaryNumber = primaryNumber;
	}
	public Long getSecondaryNumber() {
		return secondaryNumber;
	}
	public void setSecondaryNumber(Long secondaryNumber) {
		this.secondaryNumber = secondaryNumber;
	}
	
	

}
