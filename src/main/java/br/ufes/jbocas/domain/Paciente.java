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
@Table(name = "paciente")
public class Paciente {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String cpf;
	@Column
	private String prontuario;
	@Column
	private String nome;
	@Column
	private String sus;
	@Column
	private String nomeMae;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	private Date inicioTratamento;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	private Date nascimento;
	@Column
	private String sexo;
	@Column
	private String cor;
	@Column
	private String profissao;
	@Column
	private String naturalidade;
	@Column
	private String escolaridade;
	@Column
	private String estadoCivil;
	@Column
	private String telefone;
	@Column
	private String municipio;
	@Column
	private String cep;
	@Column
	private String cidade;
	@Column
	private String endereco;
	@Column
	private String fumante;
	@Column
	private String freqFumante;
	@Column
	private String tipoFumante;
	@Column
	private String etilista;
	@Column
	private String freqEtilista;
	@Column
	private String tipoEtilista;
	@Column
	private String expoSol;
	@Column
	private String freqSol;
	@Column
	private String cancerFamilia;
	@Column
	private String regiaoCancerFamilia;
	@Column
	private String parentescoCancerFamilia;
	@Column
	private String problemasCardiovasculares;
	@Column
	private String diabetes;
	@Column
	private String projDisci;
	@OneToMany	(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn (name="pacienteId")
	private List<Atendimento> atendimentos;
	@OneToMany	(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn (name="pacienteId")
	private List<DoencasSistemicas> doencasSistemicas;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	private Date ultimaRevisao;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	private Date proxRevisao;
}
