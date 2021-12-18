package br.com.rvv.gestao.controller.dto;

import java.time.LocalDateTime;

public class UploadLogDto {
	
	private String tipo;
	private LocalDateTime data;
	
	public UploadLogDto(String tipo, LocalDateTime data) {
		this.tipo = tipo;
		this.data = data;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}

	
}
