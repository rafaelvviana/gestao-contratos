package br.com.rvv.gestao.controller.dto;

import br.com.rvv.gestao.model.Regra;

public class ResumoContratosDto {
	
	private Regra regra;	
	private int quantidade;
	private long percentual;
	
	public ResumoContratosDto(Regra regra,int quantidade, long percentual) {
		this.regra = regra;
		this.quantidade = quantidade;
		this.percentual = percentual;
	}
	
	public Regra getRegra() {
		return regra;
	}

	public void setRegra(Regra regra) {
		this.regra = regra;
	}

	public long getPercentual() {
		return percentual;
	}

	public void setPercentual(long percentual) {
		this.percentual = percentual;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
