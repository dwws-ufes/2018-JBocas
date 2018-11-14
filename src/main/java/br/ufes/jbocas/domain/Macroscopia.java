package br.ufes.jbocas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "macroscopia")
public class Macroscopia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int numeroFragmentos;
	
	private String forma;
	
	private String superficie;
	
	private String consistencia;
	
	private String coloracao;
	
	private String tamanho;
}
