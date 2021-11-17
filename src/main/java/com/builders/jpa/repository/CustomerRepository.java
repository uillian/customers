/**
 * 
 */
package com.builders.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.builders.jpa.model.Customer;

/**
 * Repositório para gerência de dados do Cliente
 * @author uillian.nascimento
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT c FROM Customer c WHERE c.eMail = :email" )
	public Customer findByEmail (@Param("email") String email);

	@Query("SELECT c FROM Customer c WHERE c.name = 'Paul Ziberman'" )
	public Customer findByName(@Param("name") String name);

	@Query("SELECT c FROM Customer c " +
           "WHERE LOWER(c.name) like %:searchTerm% " +
           "OR LOWER(c.eMail) like %:searchTerm% " +
           "OR c.birthDate like %:searchTerm% ")
	public Page<Customer> search(@Param("searchTerm") String searchTerm, Pageable pageable);

}
