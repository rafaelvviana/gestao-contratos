package br.com.rvv.gestao.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UploadLog {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String tipo;	
	private LocalDateTime data;
	
	public UploadLog(String tipo, LocalDateTime data) {
		this.tipo = tipo;
		this.data = data;	
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
