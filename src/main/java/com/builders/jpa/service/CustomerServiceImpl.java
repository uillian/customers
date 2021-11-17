package com.builders.jpa.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.builders.jpa.model.Customer;
import com.builders.jpa.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository repository;
	
	public Customer findByName(String name) {
		return repository.findByName(name);
	}
	
	public Customer findById(Long id) {
		return repository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public Customer findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public Page<Customer> search(String searchTerm, int page, int size) {
		Pageable p = PageRequest.of(page, size);
		return repository.search(searchTerm.toLowerCase(),p);
	}

	public Customer update(Long customerId, Customer customerDetails) {
		return repository.findById(customerId)
				.map(customer -> {
					customer.setAddresses(customerDetails.getAddresses());
					customer.setBirthDate(customerDetails.getBirthDate());
					customer.setEMail(customerDetails.getEMail());
					customer.setGender(customerDetails.getGender());
					customer.setName(customerDetails.getName());
					customer.setPhones(customerDetails.getPhones());
			        return repository.save(customer);
			      })
				.orElseThrow(EntityNotFoundException::new);
	}
	
	public Customer save(Customer customerDetails) {
		return repository.save(customerDetails);
	}

	public void delete(Long customerId) {
		repository.deleteById(customerId);
	}
}
