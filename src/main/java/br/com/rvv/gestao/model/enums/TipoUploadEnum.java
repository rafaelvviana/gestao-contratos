package br.com.rvv.gestao.model.enums;

public enum TipoUploadEnum {

	CONTRATO("contrato"),
	DEMANDAS("demanda"),
	SALDOS("saldo"),
	PENDENCIAS("pendencia");
	
	private String descricao;
	
	private TipoUploadEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoUploadEnum fromString(String value){        
		if(value.equals(DEMANDAS.descricao)) {
				return DEMANDAS;
		} else
		if(value.equals(SALDOS.descricao)) {
			return SALDOS;
		} else
		if(value.equals(PENDENCIAS.descricao)) {
			return PENDENCIAS;
		} else {		
			return CONTRATO;
		}
	}
	
}
