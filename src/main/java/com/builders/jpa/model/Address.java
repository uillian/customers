/**
 * 
 */
package com.builders.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * Classe Address - Representação do Objeto de Negócio Endereço do Cliente
 * @author uillian.nascimento
 *
 */
@Entity
@Table(name = "Address")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5270724013945766068L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
	
	@Column(name="postalAddress", nullable=false)
	private String postalAddress;
	
	@Column(name="postalCode", nullable=false)
	private String postalCode;

}
