package br.com.rvv.gestao.controller.dto;

public class ResumoDemandasDto {

	public String nome;
	public int quantidade;
	
	public ResumoDemandasDto(String nome, int quantidade) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
	}	
}
