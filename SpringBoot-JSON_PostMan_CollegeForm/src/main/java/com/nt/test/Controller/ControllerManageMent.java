package com.nt.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.test.IPersonRepo;
import com.nt.test.Entity.Person;
import com.nt.test.Service.Iservice;

@RestController
public class ControllerManageMent {
	@Autowired
	private Iservice service;
	@PostMapping("/addPerson")
	public ResponseEntity<Person>addPerson(@RequestBody Person person){
		Person persondata=service.createPerson(person);
		System.out.println("ControllerManageMent.addPerson()==>"+persondata.getId());
		return ResponseEntity.ok(persondata);
	}

}
