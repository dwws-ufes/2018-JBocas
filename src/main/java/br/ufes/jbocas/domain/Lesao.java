package br.ufes.jbocas.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "lesao")
public class Lesao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String historico;
	
	private String regiao;
	
	private String encaminhamentos;
	
/*    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "lesao_hipotese",
            joinColumns = @JoinColumn(name = "lesao_id"),
            inverseJoinColumns = @JoinColumn(name = "hipotese_id"))*/
	
	private String hipoteses;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Fotos> fotos = new ArrayList<Fotos>();
	
	@OneToOne(fetch=FetchType.LAZY)
	private Laudo laudo;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Macroscopia macroscopia;
	
}
