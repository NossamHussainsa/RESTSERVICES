package com.nt.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.test.Entity.University;
import com.nt.test.Service.IServiceManagent;

@RestController
public class ControllerManagement {
	@Autowired
	private IServiceManagent service;
	@PostMapping(path = "/addUniversity",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<University >addUniversity(@RequestBody University university){
		System.out.println("ControllerManagement.addUniversity()==>"+university.getId());
		if(service.adduniversity(university)!=null) {
			return  ResponseEntity.ok(service.adduniversity(university));
		}
		else
		{
			return  (ResponseEntity<University>) ResponseEntity.status(500);
		}
	}

}
