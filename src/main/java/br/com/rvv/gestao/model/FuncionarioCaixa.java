package br.com.rvv.gestao.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.rvv.gestao.model.enums.CargoCaixaEnum;

@Entity
public class FuncionarioCaixa  {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
		
	@Enumerated
	private CargoCaixaEnum cargo;

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
	
	public FuncionarioCaixa() {}
	
	public FuncionarioCaixa(String nome, CargoCaixaEnum cargo) {
		this.nome = nome;
		this.cargo = cargo;
	}

	public CargoCaixaEnum getCargo() {
		return cargo;
	}

	public void setCargo(CargoCaixaEnum cargo) {
		this.cargo = cargo;
	}
	
	
}
