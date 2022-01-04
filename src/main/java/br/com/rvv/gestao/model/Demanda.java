package br.com.rvv.gestao.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Demanda {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
		
	private LocalDate dataEncaminhamento;
	private LocalDate dataEntrada;
	private String tempoExecucao;
	private String tempoDeslocamento;
	private String protocolo;
	
	private String tipoProcolo;
	private String documento;
	private String tipoDocumento;	
		
	private String tomadorNome;
	private String tipoDemanda;
	private String subTipoDemanda;	
	private long numeroOperacao;
	
	private String origem;
	@Column(length = 1000)
	private String assunto;
	private String situacao;
	private String externo;
	private LocalDate dataEncaminhamentoExterno;
	private String obsExterno;
	
	private String situacaoSubTipo;
	private String situacaoTotal;
	private String prazoSubTipo;
	private int prazoTotal;
	private String responsavel;
	private String equipe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UnidadeCaixa unidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Contrato contrato;
	
	public UnidadeCaixa getUnidade() {
		return unidade;
	}
	public void setUnidade(UnidadeCaixa unidade) {
		this.unidade = unidade;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDataEncaminhamento() {
		return dataEncaminhamento;
	}
	public void setDataEncaminhamento(LocalDate dataEncaminhamento) {
		this.dataEncaminhamento = dataEncaminhamento;
	}
	public LocalDate getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getTempoExecucao() {
		return tempoExecucao;
	}
	public void setTempoExecucao(String tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}
	public String getTempoDeslocamento() {
		return tempoDeslocamento;
	}
	public void setTempoDeslocamento(String tempoDeslocamento) {
		this.tempoDeslocamento = tempoDeslocamento;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public String getTipoProcolo() {
		return tipoProcolo;
	}
	public void setTipoProcolo(String tipoProcolo) {
		this.tipoProcolo = tipoProcolo;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getTomadorNome() {
		return tomadorNome;
	}
	public void setTomadorNome(String tomadorNome) {
		this.tomadorNome = tomadorNome;
	}
	public String getTipoDemanda() {
		return tipoDemanda;
	}
	public void setTipoDemanda(String tipoDemanda) {
		this.tipoDemanda = tipoDemanda;
	}
	public String getSubTipoDemanda() {
		return subTipoDemanda;
	}
	public void setSubTipoDemanda(String subTipoDemanda) {
		this.subTipoDemanda = subTipoDemanda;
	}
	public long getNumeroOperacao() {
		return numeroOperacao;
	}
	public void setNumeroOperacao(long numeroOperacao) {
		this.numeroOperacao = numeroOperacao;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getExterno() {
		return externo;
	}
	public void setExterno(String externo) {
		this.externo = externo;
	}
	public String getSituacaoSubTipo() {
		return situacaoSubTipo;
	}
	public void setSituacaoSubTipo(String situacaoSubTipo) {
		this.situacaoSubTipo = situacaoSubTipo;
	}
	public String getSituacaoTotal() {
		return situacaoTotal;
	}
	public void setSituacaoTotal(String situacaoTotal) {
		this.situacaoTotal = situacaoTotal;
	}
	public String getPrazoSubTipo() {
		return prazoSubTipo;
	}
	public void setPrazoSubTipo(String prazoSubTipo) {
		this.prazoSubTipo = prazoSubTipo;
	}
	public int getPrazoTotal() {
		return prazoTotal;
	}
	public void setPrazoTotal(int prazoTotal) {
		this.prazoTotal = prazoTotal;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	public LocalDate getDataEncaminhamentoExterno() {
		return dataEncaminhamentoExterno;
	}
	public void setDataEncaminhamentoExterno(LocalDate dataEncaminhamentoExterno) {
		this.dataEncaminhamentoExterno = dataEncaminhamentoExterno;
	}
	public String getObsExterno() {
		return obsExterno;
	}
	public void setObsExterno(String obsExterno) {
		this.obsExterno = obsExterno;
	}
	
}
