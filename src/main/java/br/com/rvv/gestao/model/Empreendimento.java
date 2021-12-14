package br.com.rvv.gestao.model;

import java.time.LocalDate;

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
	private String apelidoEmpreendimento;	
	@Column(length = 1000)
	private String observacaoEmpreendimento;
	private LocalDate dataSelecaoEmpreendimento;
	
	@ManyToOne
	private Municipio municipio;
	@ManyToOne
	private Programa programa;
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelidoEmpreendimento() {
		return apelidoEmpreendimento;
	}

	public void setApelidoEmpreendimento(String apelidoEmpreendimento) {
		this.apelidoEmpreendimento = apelidoEmpreendimento;
	}

	public String getObservacaoEmpreendimento() {
		return observacaoEmpreendimento;
	}

	public void setObservacaoEmpreendimento(String observacaoEmpreendimento) {
		this.observacaoEmpreendimento = observacaoEmpreendimento;
	}

	public LocalDate getDataSelecaoEmpreendimento() {
		return dataSelecaoEmpreendimento;
	}

	public void setDataSelecaoEmpreendimento(LocalDate dataSelecaoEmpreendimento) {
		this.dataSelecaoEmpreendimento = dataSelecaoEmpreendimento;
	}

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

	public Empreendimento(String nomeEmpreendimento, String objeto, String localidade, String logradouro, 
			String apelidoEmpreendimento, String observacaoEmpreendimento, LocalDate dataSelecaoEmpreendimento,
			Municipio municipio, Programa programa) {
		this.nome = nomeEmpreendimento;
		this.objeto = objeto;
		this.localidade = localidade;
		this.logradouro = logradouro;
		this.municipio = municipio;
		this.programa = programa;
		this.apelidoEmpreendimento = apelidoEmpreendimento;
		this.observacaoEmpreendimento = observacaoEmpreendimento;
		this.dataSelecaoEmpreendimento = dataSelecaoEmpreendimento;		
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
