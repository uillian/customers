/**
 * 
 */
package com.builders.jpa.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.builders.jpa.model.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
 
/**
 * RestController responsável por retornar dados dos Clientes por ID, email,
 * nome e um termo especifico (search Term)
 * 
 * @author uillian.nascimento
 *
 */
public interface CustomerController {

	@Operation(summary = "Find a Customer by a specific Term")
	public ResponseEntity<Page<Customer>> search(@Parameter(description = "Term to search") String searchTerm, 
			@Parameter(description = "page number (pagination)") int page, 
			@Parameter(description = "page size (pagination)") int size);

	@Operation(summary = "Find a Customer by Name")
	public ResponseEntity<Customer> findByName(@Parameter(description = "name of the Customer") String name);

	@Operation(summary = "Find a Customer by Email")
	public ResponseEntity<Customer> findByEmail(@Parameter(description = "email of the Customer") String email);

	@Operation(summary = "Find a Customer by ID")
	public ResponseEntity<Customer> findById(@Parameter(description = "ID of the Customer") Long id);

	@Operation(summary = "Update a Customer")
	public ResponseEntity<Customer> update(@Parameter(description = "ID of the Customer") Long customerId,
			@Parameter(description = "Datas of Customer who will be updated") Customer customerDetails);

	@Operation(summary = "Add a new Customer")
	public ResponseEntity<Customer> add(@Parameter(description = "Datas of the new Customer") Customer customerDetails);

	@Operation(summary = "Delete a Customer by ID")
	public ResponseEntity<?> delete(@Parameter(description = "ID of the Customer") Long customerId);
}
