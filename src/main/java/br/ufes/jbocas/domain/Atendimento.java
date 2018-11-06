package br.ufes.jbocas.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "atendimento")
public class Atendimento {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String clinica;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	private Date data;
	@Column
	private String local;
	@OneToMany	(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn (name="atendimentoId")
	private List<Lesao> lesoes;
}
