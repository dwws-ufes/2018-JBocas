package br.ufes.jbocas.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "laudo")
public class Laudo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero;
	
	@Temporal(TemporalType.DATE)
	private Date entrada;
	
	private String qualidadePeca;
	
	private String profissional;
	
	private String setor;
	
	private String resumoClinico;
}
