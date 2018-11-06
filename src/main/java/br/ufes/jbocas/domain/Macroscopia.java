package br.ufes.jbocas.domain;

import javax.persistence.Column;
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
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int numeroFragmentos;
	@Column
	private String forma;
	@Column
	private String superficie;
	@Column
	private String consistencia;
	@Column
	private String coloracao;
	@Column
	private String tamanho;
}
