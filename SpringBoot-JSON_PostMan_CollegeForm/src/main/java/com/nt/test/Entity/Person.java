package com.nt.test.Entity;

import com.nt.test.Embedded.MobileNumbers;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Person_seq")
	@SequenceGenerator(name =  "Person_seq",allocationSize = 1,initialValue = 1001)
	private Integer id;
	
	private String name;
	private Integer age;
	private String gender;
	private Long adhar;
	
	@Embedded
	private MobileNumbers mobileNumbers;
	@Embedded
	private Location location;
	@Embedded
	private Job job;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getAdhar() {
		return adhar;
	}
	public void setAdhar(Long adhar) {
		this.adhar = adhar;
	}
	public MobileNumbers getMobileNumbers() {
		return mobileNumbers;
	}
	public void setMobileNumbers(MobileNumbers mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	
}
