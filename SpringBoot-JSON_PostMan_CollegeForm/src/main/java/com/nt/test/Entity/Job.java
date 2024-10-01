package com.nt.test.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;

@Embeddable
public class Job {
	private String role;
	private String companyName;
	private BigDecimal salary;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	

}
