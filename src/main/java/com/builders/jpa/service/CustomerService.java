/**
 * 
 */
package com.builders.jpa.service;

import org.springframework.data.domain.Page;

import com.builders.jpa.model.Customer;

/**
 * Service para gestão do CustomerRepository
 * 
 * @author uillian.nascimento
 *
 */
public interface CustomerService {

	public Customer findByName(String name);

	public Customer findById(Long id);

	public Customer findByEmail(String email);

	public Page<Customer> search(String searchTerm, int page, int size);

	public Customer update(Long customerId, Customer customerDetails);

	public Customer save(Customer customerDetails);

	public void delete(Long customerId);
}
