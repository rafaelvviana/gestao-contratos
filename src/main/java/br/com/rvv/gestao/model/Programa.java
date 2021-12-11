package br.com.rvv.gestao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Programa {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	private String objetivo;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Programa() {}
	
	public Programa(String nomePrograma, String objetivo) {
		this.nome = nomePrograma;
		this.objetivo = objetivo;
	}

	
	
}
