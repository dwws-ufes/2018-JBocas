package br.ufes.jbocas.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "paciente")
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cpf = new String();
	
	private String prontuario;
	
	private String nome;
	
	private String sus;
	
	private String nomeMae;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicioTratamento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate nascimento;
	
	@Transient
	private int idade;
	
	private String sexo;
	
	private String cor;
	
	private String profissao;
	
	private String naturalidade;
	
	private String escolaridade;
	
	private String estadoCivil;
	
	private String telefone;
	
	private String municipio;
	
	private String cep;
	
	private String cidade;
	
	private String endereco;
	
	private String fumante;
	
	private String freqFumante;
	
	private String tipoFumante;
	
	private String etilista;
	
	private String freqEtilista;
	
	private String tipoEtilista;
	
	private String expoSol;
	
	private String freqSol;
	
	private String cancerFamilia;
	
	private String regiaoCancerFamilia;
	
	private String parentescoCancerFamilia;
	
	private String problemasCardiovasculares;
	
	private String diabetes;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ultimaRevisao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate proxRevisao;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<DoencasSistemicas> doencasSistemicas = new ArrayList<DoencasSistemicas>();
}
