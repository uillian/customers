/**
 * 
 */
package com.builders.jpa.main;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.builders.jpa.model.Address;
import com.builders.jpa.model.Customer;
import com.builders.jpa.model.Gender;
import com.builders.jpa.model.Phone;
import com.builders.jpa.model.PhoneType;
import com.builders.jpa.repository.CustomerRepository;

import lombok.AllArgsConstructor;

/**
 * Classe Main para carregamento de dados default e start do Spring Boot 
 * @author uillian.nascimento
 *
 */

@SpringBootApplication
@AllArgsConstructor
@EntityScan("com.builders.jpa.model")
@ComponentScan( "com.builders" )
@EnableJpaRepositories("com.builders.jpa.repository")
public class MainClass implements CommandLineRunner {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public static void main (String[] args) {
        SpringApplication.run(MainClass.class, args);
    }
	
	@Override
	public void run(String... args) throws Exception {
		Address addresses1 = Address.builder()
    			.postalAddress("Avenida Brigadeiro Faria Lima, 1779")
    			.postalCode("01452-914").build();
		
		Address addresses2 = Address.builder()
				.postalAddress("Avenida Giovanni Gronchi, 200")
				.postalCode("05651-000").build();
    	
    	Phone phone1 = Phone.builder()
    			.number("+55 11 98776-2333")
    			.type(PhoneType.MOBILE).build();
    	
    	Phone phone2 = Phone.builder()
    			.number("+55 11 3221-8888")
    			.type(PhoneType.LANDLINE).build();
    	
    	Phone phone3 = Phone.builder()
    			.number("+55 11 955662-0997")
    			.type(PhoneType.MOBILE).build();
    			
		Customer c1 = Customer.builder()
				.name("Paul Ziberman")
				.eMail("pziberman@outlook.com")
				.gender(Gender.MALE)
				.addresses(Arrays.asList(addresses1))
				.phones(Arrays.asList(phone1))
				.birthDate(LocalDate.of(1974, 8, 10))
				.build();
		
		Customer c2 = Customer.builder()
				.name("Linda Frankly")
				.eMail("lindafranfly@gmail.com")
				.gender(Gender.FEMALE)
				.addresses(Arrays.asList(addresses2))
				.phones(Arrays.asList(phone2,phone3))
				.birthDate(LocalDate.of(1978, 6, 14))
				.build();
		
		customerRepository.saveAll(Arrays.asList(c1,c2));
	}
}
