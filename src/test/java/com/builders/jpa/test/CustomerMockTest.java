package com.builders.jpa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.builders.jpa.model.Address;
import com.builders.jpa.model.Customer;
import com.builders.jpa.model.Gender;
import com.builders.jpa.model.Phone;
import com.builders.jpa.model.PhoneType;
import com.builders.jpa.repository.CustomerRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CustomerMockTest.class})
@AutoConfigureMockMvc
public class CustomerMockTest {
	
	@MockBean
	CustomerRepository mockRepository;
	
	@Test
	public void testMockRepositoryFindByName( ) {
		
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
    			
		Customer mockCustomer1 = Customer.builder()
				.name("Paul Ziberman")
				.eMail("pziberman@outlook.com")
				.gender(Gender.MALE)
				.addresses(Arrays.asList(addresses1))
				.phones(Arrays.asList(phone1))
				.birthDate(LocalDate.of(1974, 8, 10))
				.build();
		
		Customer mockCustomer2 = Customer.builder()
				.name("Linda Frankly")
				.eMail("lindafranfly@gmail.com")
				.gender(Gender.FEMALE)
				.addresses(Arrays.asList(addresses2))
				.phones(Arrays.asList(phone2,phone3))
				.birthDate(LocalDate.of(1978, 6, 14))
				.build();
		
		Mockito.when(mockRepository.findByName("Paul Ziberman")).thenReturn(mockCustomer1);
		Mockito.when(mockRepository.findByName("Linda Frankly")).thenReturn(mockCustomer2);
		
		Customer customerTest1 = mockRepository.findByName("Paul Ziberman");
		Customer customerTest2 = mockRepository.findByName("Linda Frankly");
		
		assertEquals(mockCustomer1, customerTest1);
		assertEquals(mockCustomer2, customerTest2);
		
	}

}
