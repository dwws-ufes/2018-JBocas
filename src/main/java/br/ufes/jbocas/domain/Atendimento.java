package br.ufes.jbocas.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "atendimento")
public class Atendimento {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String clinica;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAtendimento = LocalDate.now();
	
	@Column
	private String local;
	
	@Column
	private String projDisci;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Lesao> lesoes = new ArrayList<Lesao>();
	
	@OneToOne(fetch=FetchType.LAZY)
	private Requisicao requisicoes;
	
}
