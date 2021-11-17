/**
 * 
 */
package com.builders.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe Address - Representação do Objeto de Negócio Telefone do Cliente
 * @author uillian.nascimento
 *
 */
@Entity
@Table(name = "Phone")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Phone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5409092258925770035L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
	
	@Column(name="number", nullable=false)
	private String number;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="type")  
	private PhoneType type;
	
}
