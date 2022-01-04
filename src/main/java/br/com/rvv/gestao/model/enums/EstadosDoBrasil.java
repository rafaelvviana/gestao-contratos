package br.com.rvv.gestao.model.enums;

public enum EstadosDoBrasil {
	
	AM("AM"),
	AC("AC"),
	PA("PA"),
	RR("RR"),
	RO("RO"),
	MA("MA"),
	PI("PI"),
	CE("CE"),
	RN("RN"),
	PB("PB"),
	PE("PE"),
	AL("AL"),
	SE("SE"),
	BA("BA"),
	RJ("RJ"),
	MG("MG"),
	ES("ES"),
	SP("SP"),
	MT("MT"),
	MS("MS"),
	GO("GO"),
	DF("DF"),
	SC("SC"),
	PR("PR"),
	RS("RS");
	
	private String descricao;
	
	private EstadosDoBrasil(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
