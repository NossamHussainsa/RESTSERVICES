package com.nt.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.test.Entity.AccountHolder;
import com.nt.test.Service.IAccount;

@RestController
public class ControllerManagement {
	@Autowired
	private IAccount service;


	@PreAuthorize("hasRole('USER')")
	@PostMapping(path = "/addAccount", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> addNewAccount(@RequestBody AccountHolder accountholder) {
		String result = service.addAccount(accountholder);
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping(path = "/getAccountHolder/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AccountHolder> getAccountHolder(@PathVariable Integer id) {
		AccountHolder value = service.getAccountDetails(id);
		return new ResponseEntity<AccountHolder>(value, HttpStatus.FOUND);

	}


	@PreAuthorize("hasRole('USER')")
	@GetMapping(path = "/getallAccountHolders", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<AccountHolder>> getAllAccountHolder() {
		List<AccountHolder> value = service.getallHoldersLIst();
		return ResponseEntity.ok(value);
	}


	@PreAuthorize("hasRole('USER')")
	@PutMapping(path = "/updateAccountAmount/{id}/{amount}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> updateAmount(@PathVariable Integer id, @PathVariable Double amount) {
		String result = service.addAmount(id, amount);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}


	@PreAuthorize("hasRole('USER')")
	@DeleteMapping(path = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
		String result = service.deleteAccountHolder(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

}
