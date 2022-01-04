package br.com.rvv.gestao.model.enums;

public enum PaginasEnum {
	
	HOME("home"),
	ERRO("pages/erro-inesperado.html"),
	NAO_ENCONTRADO("pages/pagina-nao-encontrada.html"),
	SEM_DADOS("pages/sem-dados.html"),
	DEMANDA_LISTA("demanda/listaDemandas.html"),
	UPLOAD_AGUARDE("upload/formUploadAguarde"),
	UPLOAD_FORM("upload/formUpload");

	private String descricao;
	
	private PaginasEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
