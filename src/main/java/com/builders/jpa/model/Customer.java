package com.builders.jpa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe Address - Representação do Objeto de Negócio Cliente
 * @author uillian.nascimento
 *
 */
@Entity
@Table(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8259681575815127826L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable=false)
	private String name;

	@Column(name="eMail",nullable=true)
	private String eMail;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="gender")  
	private Gender gender;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<Address> addresses;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<Phone> phones;
	
	@Column(name="birthDate", nullable=false)
	private LocalDate birthDate;
	
	@Formula(value = "DATE_FORMAT(FROM_DAYS(DATEDIFF(now(),birth_date)), '%Y')+0")
	@Setter(value = AccessLevel.NONE)
    private int age;
}
