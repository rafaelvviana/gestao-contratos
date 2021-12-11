package br.com.rvv.gestao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Empreendimento {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	@Column(length = 1000)
	private String objeto;
	private String localidade;
	private String logradouro;

	@ManyToOne
	private Municipio municipio;
	@ManyToOne
	private Programa programa;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeEmpreendimento() {
		return nome;
	}

	public void setNomeEmpreendimento(String nomeEmpreendimento) {
		this.nome = nomeEmpreendimento;
	}

	public Empreendimento() {
	}

	public Empreendimento(String nomeEmpreendimento, String objeto, String localidade, String logradouro, Municipio municipio,
			Programa programa) {
		this.nome = nomeEmpreendimento;
		this.objeto = objeto;
		this.localidade = localidade;
		this.logradouro = logradouro;
		this.municipio = municipio;
		this.programa = programa;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

}
