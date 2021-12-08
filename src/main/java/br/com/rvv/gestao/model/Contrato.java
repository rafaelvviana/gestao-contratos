package br.com.rvv.gestao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contrato {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	
	// DADOS DA OPERAÇÃO
	private int numeroOperacao;
	private int dvOperacao;
	private long numeroSiconv;
	private long numeroPropostaSiconv;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumeroOperacao() {
		return numeroOperacao;
	}
	public void setNumeroOperacao(int numeroOperacao) {
		this.numeroOperacao = numeroOperacao;
	}
	public int getDvOperacao() {
		return dvOperacao;
	}
	public void setDvOperacao(int dvOperacao) {
		this.dvOperacao = dvOperacao;
	}
	public long getNumeroSiconv() {
		return numeroSiconv;
	}
	public void setNumeroSiconv(long numeroSiconv) {
		this.numeroSiconv = numeroSiconv;
	}
	public long getNumeroPropostaSiconv() {
		return numeroPropostaSiconv;
	}
	public void setNumeroPropostaSiconv(long numeroPropostaSiconv) {
		this.numeroPropostaSiconv = numeroPropostaSiconv;
	}	

	
}
