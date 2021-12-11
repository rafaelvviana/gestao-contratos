package br.com.rvv.gestao.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contrato {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	
	private int numeroOperacao;
	private int dvOperacao;
	private long numeroSiconv;
	private long numeroPropostaSiconv;
	protected String tipoProjeto;
	
	protected String situacaoCAUC;
	protected String situacaoCT;
	protected String estagioCT;
	protected String situacaoConvenioSICONV;	
	protected String permiteAdiantarParcela;	
	protected String SolicitouLiberarParcela;
	protected String CondicionanteLiberarParcela;
	protected String descricaoCondicionante;
	protected String situacaoProjetoBasicoSPA;
	
	protected long diasCorridosSemVRPL;
	protected String licitacao;
		
	protected BigDecimal vi;
	protected BigDecimal vr;
	protected BigDecimal cp1;
	protected BigDecimal cp2;
	protected BigDecimal vrEmpenhado;
	protected BigDecimal vrCreditado;
	protected BigDecimal vrSolicitado;
	protected BigDecimal vrNecessidadeFinanceira;
	protected BigDecimal vrDesbloqueado;
	protected BigDecimal vrSaldoCreditado;
	protected BigDecimal cpCreditada;
	protected BigDecimal cpDesbloqueado;
	protected BigDecimal viPTSRepasse;
	protected BigDecimal viPTSContrapartida;
	protected BigDecimal viPTSDesbloqueado;
	protected BigDecimal viQCIVigente;
	protected BigDecimal percViDesbloqueado;
	protected BigDecimal percViQCIDesbloqueado;
	protected BigDecimal percObraInformado;
	protected BigDecimal percObraExecutado;	
	protected BigDecimal percObraLiberacao;
	protected String situacaoObra;
	
	protected BigDecimal saldoContaCorrente;
	protected BigDecimal saldoAplicacao;
	protected BigDecimal saldoContaPoupanca;
	protected BigDecimal saldoGeral;
		
	protected BigDecimal disponivelPagamentoImediato;
	protected BigDecimal potencialPagamentoFuturo;
	protected LocalDate dataPrimeiroCredito;
	protected long diasCorridosPrimeiroCredito;
	protected long diasCorridosUltimoDesbloqueio;
	protected BigDecimal vrUltimoDesbloqueio;
	protected String situacaoPrestacaoContas;
	
	@ManyToOne
	protected UnidadeCaixa unidadeCaixa;
	
	@ManyToOne
	protected Empreendimento empreendimento;
	
	@ManyToOne
	protected FuncionarioCaixa operacional;
	
	@ManyToOne
	protected FuncionarioCaixa operacionalAtual;
	
	@ManyToOne
	protected FuncionarioCaixa engenheiro;
	
	@ManyToOne
	protected FuncionarioCaixa engenheiroAtual;

	@ManyToOne
	protected FuncionarioCaixa social;
	
	@ManyToOne
	protected FuncionarioCaixa socialAtual;
	
	@ManyToOne
	protected FuncionarioCaixa representanteCaixa;

	@ManyToOne
	protected FuncionarioCaixa representanteCaixaAtual;
		
	@ManyToOne
	protected Entidade tomadorOperacao;
	
	@ManyToOne
	protected Entidade tomadorPrincipal;
	
	@ManyToOne
	protected Entidade agentePromotorOperacao;
	
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
	public String getTipoProjeto() {
		return tipoProjeto;
	}
	public void setTipoProjeto(String tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}
	public String getSituacaoCAUC() {
		return situacaoCAUC;
	}
	public void setSituacaoCAUC(String situacaoCAUC) {
		this.situacaoCAUC = situacaoCAUC;
	}
	public String getSituacaoCT() {
		return situacaoCT;
	}
	public void setSituacaoCT(String situacaoCT) {
		this.situacaoCT = situacaoCT;
	}
	public String getEstagioCT() {
		return estagioCT;
	}
	public void setEstagioCT(String estagioCT) {
		this.estagioCT = estagioCT;
	}
	public String getSituacaoConvenioSICONV() {
		return situacaoConvenioSICONV;
	}
	public void setSituacaoConvenioSICONV(String situacaoConvenioSICONV) {
		this.situacaoConvenioSICONV = situacaoConvenioSICONV;
	}
	public String getPermiteAdiantarParcela() {
		return permiteAdiantarParcela;
	}
	public void setPermiteAdiantarParcela(String permiteAdiantarParcela) {
		this.permiteAdiantarParcela = permiteAdiantarParcela;
	}
	public String getSolicitouLiberarParcela() {
		return SolicitouLiberarParcela;
	}
	public void setSolicitouLiberarParcela(String solicitouLiberarParcela) {
		SolicitouLiberarParcela = solicitouLiberarParcela;
	}
	public String getCondicionanteLiberarParcela() {
		return CondicionanteLiberarParcela;
	}
	public void setCondicionanteLiberarParcela(String condicionanteLiberarParcela) {
		CondicionanteLiberarParcela = condicionanteLiberarParcela;
	}
	public String getDescricaoCondicionante() {
		return descricaoCondicionante;
	}
	public void setDescricaoCondicionante(String descricaoCondicionante) {
		this.descricaoCondicionante = descricaoCondicionante;
	}
	public String getSituacaoProjetoBasicoSPA() {
		return situacaoProjetoBasicoSPA;
	}
	public void setSituacaoProjetoBasicoSPA(String situacaoProjetoBasicoSPA) {
		this.situacaoProjetoBasicoSPA = situacaoProjetoBasicoSPA;
	}
	public long getDiasCorridosSemVRPL() {
		return diasCorridosSemVRPL;
	}
	public void setDiasCorridosSemVRPL(long diasCorridosSemVRPL) {
		this.diasCorridosSemVRPL = diasCorridosSemVRPL;
	}
	public String getLicitacao() {
		return licitacao;
	}
	public void setLicitacao(String licitacao) {
		this.licitacao = licitacao;
	}
	public BigDecimal getVi() {
		return vi;
	}
	public void setVi(BigDecimal vi) {
		this.vi = vi;
	}
	public BigDecimal getVr() {
		return vr;
	}
	public void setVr(BigDecimal vr) {
		this.vr = vr;
	}
	public BigDecimal getCp1() {
		return cp1;
	}
	public void setCp1(BigDecimal cp1) {
		this.cp1 = cp1;
	}
	public BigDecimal getCp2() {
		return cp2;
	}
	public void setCp2(BigDecimal cp2) {
		this.cp2 = cp2;
	}
	public BigDecimal getVrEmpenhado() {
		return vrEmpenhado;
	}
	public void setVrEmpenhado(BigDecimal vrEmpenhado) {
		this.vrEmpenhado = vrEmpenhado;
	}
	public BigDecimal getVrCreditado() {
		return vrCreditado;
	}
	public void setVrCreditado(BigDecimal vrCreditado) {
		this.vrCreditado = vrCreditado;
	}
	public BigDecimal getVrSolicitado() {
		return vrSolicitado;
	}
	public void setVrSolicitado(BigDecimal vrSolicitado) {
		this.vrSolicitado = vrSolicitado;
	}
	public BigDecimal getVrNecessidadeFinanceira() {
		return vrNecessidadeFinanceira;
	}
	public void setVrNecessidadeFinanceira(BigDecimal vrNecessidadeFinanceira) {
		this.vrNecessidadeFinanceira = vrNecessidadeFinanceira;
	}
	public BigDecimal getVrDesbloqueado() {
		return vrDesbloqueado;
	}
	public void setVrDesbloqueado(BigDecimal vrDesbloqueado) {
		this.vrDesbloqueado = vrDesbloqueado;
	}
	public BigDecimal getVrSaldoCreditado() {
		return vrSaldoCreditado;
	}
	public void setVrSaldoCreditado(BigDecimal vrSaldoCreditado) {
		this.vrSaldoCreditado = vrSaldoCreditado;
	}
	public BigDecimal getCpCreditada() {
		return cpCreditada;
	}
	public void setCpCreditada(BigDecimal cpCreditada) {
		this.cpCreditada = cpCreditada;
	}
	public BigDecimal getCpDesbloqueado() {
		return cpDesbloqueado;
	}
	public void setCpDesbloqueado(BigDecimal cpDesbloqueado) {
		this.cpDesbloqueado = cpDesbloqueado;
	}
	public BigDecimal getViPTSRepasse() {
		return viPTSRepasse;
	}
	public void setViPTSRepasse(BigDecimal viPTSRepasse) {
		this.viPTSRepasse = viPTSRepasse;
	}
	public BigDecimal getViPTSContrapartida() {
		return viPTSContrapartida;
	}
	public void setViPTSContrapartida(BigDecimal viPTSContrapartida) {
		this.viPTSContrapartida = viPTSContrapartida;
	}
	public BigDecimal getViPTSDesbloqueado() {
		return viPTSDesbloqueado;
	}
	public void setViPTSDesbloqueado(BigDecimal viPTSDesbloqueado) {
		this.viPTSDesbloqueado = viPTSDesbloqueado;
	}
	public BigDecimal getViQCIVigente() {
		return viQCIVigente;
	}
	public void setViQCIVigente(BigDecimal viQCIVigente) {
		this.viQCIVigente = viQCIVigente;
	}
	public BigDecimal getPercViDesbloqueado() {
		return percViDesbloqueado;
	}
	public void setPercViDesbloqueado(BigDecimal percViDesbloqueado) {
		this.percViDesbloqueado = percViDesbloqueado;
	}
	public BigDecimal getPercViQCIDesbloqueado() {
		return percViQCIDesbloqueado;
	}
	public void setPercViQCIDesbloqueado(BigDecimal percViQCIDesbloqueado) {
		this.percViQCIDesbloqueado = percViQCIDesbloqueado;
	}
	public BigDecimal getPercObraInformado() {
		return percObraInformado;
	}
	public void setPercObraInformado(BigDecimal percObraInformado) {
		this.percObraInformado = percObraInformado;
	}
	public BigDecimal getPercObraExecutado() {
		return percObraExecutado;
	}
	public void setPercObraExecutado(BigDecimal percObraExecutado) {
		this.percObraExecutado = percObraExecutado;
	}
	public BigDecimal getPercObraLiberacao() {
		return percObraLiberacao;
	}
	public void setPercObraLiberacao(BigDecimal percObraLiberacao) {
		this.percObraLiberacao = percObraLiberacao;
	}
	public String getSituacaoObra() {
		return situacaoObra;
	}
	public void setSituacaoObra(String situacaoObra) {
		this.situacaoObra = situacaoObra;
	}
	public BigDecimal getSaldoContaCorrente() {
		return saldoContaCorrente;
	}
	public void setSaldoContaCorrente(BigDecimal saldoContaCorrente) {
		this.saldoContaCorrente = saldoContaCorrente;
	}
	public BigDecimal getSaldoAplicacao() {
		return saldoAplicacao;
	}
	public void setSaldoAplicacao(BigDecimal saldoAplicacao) {
		this.saldoAplicacao = saldoAplicacao;
	}
	public BigDecimal getSaldoContaPoupanca() {
		return saldoContaPoupanca;
	}
	public void setSaldoContaPoupanca(BigDecimal saldoContaPoupanca) {
		this.saldoContaPoupanca = saldoContaPoupanca;
	}
	public BigDecimal getSaldoGeral() {
		return saldoGeral;
	}
	public void setSaldoGeral(BigDecimal saldoGeral) {
		this.saldoGeral = saldoGeral;
	}
	public BigDecimal getDisponivelPagamentoImediato() {
		return disponivelPagamentoImediato;
	}
	public void setDisponivelPagamentoImediato(BigDecimal disponivelPagamenttoImediato) {
		this.disponivelPagamentoImediato = disponivelPagamenttoImediato;
	}
	public BigDecimal getPotencialPagamentoFuturo() {
		return potencialPagamentoFuturo;
	}
	public void setPotencialPagamentoFuturo(BigDecimal potencialPagamentoFuturo) {
		this.potencialPagamentoFuturo = potencialPagamentoFuturo;
	}
	public LocalDate getDataPrimeiroCredito() {
		return dataPrimeiroCredito;
	}
	public void setDataPrimeiroCredito(LocalDate dataPrimeiroCredito) {
		this.dataPrimeiroCredito = dataPrimeiroCredito;
	}
	public long getDiasCorridosPrimeiroCredito() {
		return diasCorridosPrimeiroCredito;
	}
	public void setDiasCorridosPrimeiroCredito(long diasCorridosPrimeiroCredito) {
		this.diasCorridosPrimeiroCredito = diasCorridosPrimeiroCredito;
	}
	public long getDiasCorridosUltimoDesbloqueio() {
		return diasCorridosUltimoDesbloqueio;
	}
	public void setDiasCorridosUltimoDesbloqueio(long diasCorridosUltimoDesbloqueio) {
		this.diasCorridosUltimoDesbloqueio = diasCorridosUltimoDesbloqueio;
	}
	public BigDecimal getVrUltimoDesbloqueio() {
		return vrUltimoDesbloqueio;
	}
	public void setVrUltimoDesbloqueio(BigDecimal vrUltimoDesbloqueio) {
		this.vrUltimoDesbloqueio = vrUltimoDesbloqueio;
	}
	public String getSituacaoPrestacaoContas() {
		return situacaoPrestacaoContas;
	}
	public void setSituacaoPrestacaoContas(String situacaoPrestacaoContas) {
		this.situacaoPrestacaoContas = situacaoPrestacaoContas;
	}
	
	public UnidadeCaixa getUnidadeCaixa() {
		return unidadeCaixa;
	}
	public void setUnidadeCaixa(UnidadeCaixa unidadeCaixa) {
		this.unidadeCaixa = unidadeCaixa;
	}
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
	public FuncionarioCaixa getOperacional() {
		return operacional;
	}
	public void setOperacional(FuncionarioCaixa operacional) {
		this.operacional = operacional;
	}
	public FuncionarioCaixa getOperacionalAtual() {
		return operacionalAtual;
	}
	public void setOperacionalAtual(FuncionarioCaixa operacionalAtual) {
		this.operacionalAtual = operacionalAtual;
	}
	public FuncionarioCaixa getEngenheiro() {
		return engenheiro;
	}
	public void setEngenheiro(FuncionarioCaixa engenheiro) {
		this.engenheiro = engenheiro;
	}
	public FuncionarioCaixa getEngenheiroAtual() {
		return engenheiroAtual;
	}
	public void setEngenheiroAtual(FuncionarioCaixa engenheiroAtual) {
		this.engenheiroAtual = engenheiroAtual;
	}
	public FuncionarioCaixa getSocial() {
		return social;
	}
	public void setSocial(FuncionarioCaixa social) {
		this.social = social;
	}
	public FuncionarioCaixa getSocialAtual() {
		return socialAtual;
	}
	public void setSocialAtual(FuncionarioCaixa socialAtual) {
		this.socialAtual = socialAtual;
	}
	public FuncionarioCaixa getRepresentanteCaixa() {
		return representanteCaixa;
	}
	public void setRepresentanteCaixa(FuncionarioCaixa representanteCaixa) {
		this.representanteCaixa = representanteCaixa;
	}
	public FuncionarioCaixa getRepresentanteCaixaAtual() {
		return representanteCaixaAtual;
	}
	public void setRepresentanteCaixaAtual(FuncionarioCaixa representanteCaixaAtual) {
		this.representanteCaixaAtual = representanteCaixaAtual;
	}
	public Entidade getTomadorOperacao() {
		return tomadorOperacao;
	}
	public void setTomadorOperacao(Entidade tomadorOperacao) {
		this.tomadorOperacao = tomadorOperacao;
	}
	public Entidade getTomadorPrincipal() {
		return tomadorPrincipal;
	}
	public void setTomadorPrincipal(Entidade tomadorPrincipal) {
		this.tomadorPrincipal = tomadorPrincipal;
	}
	public Entidade getAgentePromotorOperacao() {
		return agentePromotorOperacao;
	}
	public void setAgentePromotorOperacao(Entidade agentePromotorOperacao) {
		this.agentePromotorOperacao = agentePromotorOperacao;
	}
	
}
