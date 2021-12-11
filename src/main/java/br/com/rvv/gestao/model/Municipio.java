package br.com.rvv.gestao.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.rvv.gestao.model.enums.EstadosDoBrasil;

@Entity
public class Municipio {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
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

	public EstadosDoBrasil getEstado() {
		return estado;
	}

	public void setEstado(EstadosDoBrasil estado) {
		this.estado = estado;
	}

	@Enumerated
	private EstadosDoBrasil estado;
	
		public Municipio() {}
	
	public Municipio(String nomeMunicipio) {
		this.nome = nomeMunicipio;
	}

	public Municipio(EstadosDoBrasil estado) {
		super();
		this.estado = estado;
	}
	
	
}
