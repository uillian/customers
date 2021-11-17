package com.builders.jpa.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.builders.jpa.model.Customer;
import com.builders.jpa.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl implements CustomerController {

	@Autowired
	CustomerService service;

	@GetMapping(value = "/search")
	public ResponseEntity<Page<Customer>> search(@RequestParam(value = "searchTerm", required = true) String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return ResponseEntity.ok().body(service.search(searchTerm, page, size));
	}

	@GetMapping(value = "/findByName/{name}")
	public ResponseEntity<Customer> findByName(@PathVariable String name) {
		 return ResponseEntity.ok(service.findByName(name));
	}

	@GetMapping(value = "/findByEmail/{email}")
	public ResponseEntity<Customer> findByEmail(@PathVariable String email) {
		return ResponseEntity.ok().body(service.findByEmail(email));
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(service.findById(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> update(@PathVariable(value = "id") Long customerId,
			@RequestBody Customer customerDetails) {
		try {
			return ResponseEntity.ok().body(service.update(customerId, customerDetails));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Customer> add(@RequestBody Customer customerDetails) {
		return ResponseEntity.ok().body(service.save(customerDetails));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long customerId) {
		service.delete(customerId);
		return ResponseEntity.noContent().build();
	}
}
