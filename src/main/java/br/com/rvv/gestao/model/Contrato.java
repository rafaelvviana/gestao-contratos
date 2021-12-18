package br.com.rvv.gestao.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Contrato {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	
	private long numeroOperacao;
	private int dvOperacao;
	private long numeroSiconv;
	private long numeroPropostaSiconv;
	private String tipoProjeto;
	
	private String situacaoCAUC;
	private String situacaoCT;
	private String estagioCT;
	private String situacaoConvenioSICONV;	
	private String permiteAdiantarParcela;	
	private String solicitouLiberarParcela;
	private String condicionanteLiberarParcela;
	private String descricaoCondicionante;
	private String situacaoProjetoBasicoSPA;
	
	private long diasCorridosSemVRPL;
	private String licitacao;
		
	private BigDecimal vi;
	private BigDecimal vr;
	private BigDecimal cp1;
	private BigDecimal cp2;
	private BigDecimal vrEmpenhado;
	private BigDecimal vrCreditado;
	private BigDecimal vrSolicitado;
	private BigDecimal vrNecessidadeFinanceira;
	private BigDecimal vrDesbloqueado;
	private BigDecimal vrSaldoCreditado;
	private BigDecimal cpCreditada;
	private BigDecimal cpDesbloqueado;
	private BigDecimal viPTSRepasse;
	private BigDecimal viPTSContrapartida;
	private BigDecimal viPTSDesbloqueado;
	private BigDecimal viQCIVigente;
	private BigDecimal percViDesbloqueado;
	private BigDecimal percViQCIDesbloqueado;
	private BigDecimal percObraInformado;
	private BigDecimal percObraExecutado;	
	private BigDecimal percObraLiberacao;
	private String situacaoObra;
	
	private BigDecimal saldoContaCorrente;
	private BigDecimal saldoAplicacao;
	private BigDecimal saldoContaPoupanca;
	private BigDecimal saldoGeral;
		
	private BigDecimal disponivelPagamentoImediato;
	private BigDecimal potencialPagamentoFuturo;
	private LocalDate dataPrimeiroCredito;
	private long diasCorridosPrimeiroCredito;
	private long diasCorridosUltimoDesbloqueio;
	private BigDecimal vrUltimoDesbloqueio;
	private String situacaoPrestacaoContas;
	
	private String agencia;
	private String nomeAgencia;
	private String contaCorrente;
	private String contaPoupanca;
	private String idExterna;
	private String portaria;
	private String simplificado;
	private String sr;
	private String obtv;
	private String cliente;
	private String gestor;
	private String impositivo;
	private String parlamentar;
	
	@Column(length = 1000)
	private String etiquetasDaOperacao;
	
	@Column(length = 1000)
	private String ultimoHistorico;
	@Column(length = 1200)
	private String situacaoAtualDetalhada;
	private String autorAlteracao;
	private String paralisadas;

	private LocalDate rPrestacaoContas;
	private LocalDate dataAssinatura;
	private LocalDate dataVigencia;
	
	private LocalDate dataEnvioMandataria;
	private LocalDate dataPublicacaoDOU;
	private LocalDate vencimentoSuspensiva;
	private LocalDate dataProjetoBasicoSPA;
	private LocalDate dataLAE;
	private LocalDate dataAutorizacaoSPA;
	private LocalDate dataVRPL;
	private LocalDate dataDeclInícioObraTomador;
	private LocalDate dataAutorizacaoObra;
	private LocalDate dataUltimoCredito;
	private LocalDate dataUltimaVistoria;
	private LocalDate dataDeDesbloqueio;
	private LocalDate dataAprovacaoPrestContasCaixa;
	private LocalDate dataUltimoHistorico;
	private LocalDate dataUltimaAlteracao;
	private LocalDate dataAio;
	private LocalDate dataEntrada;
	private LocalDate dataEncaminhamento;
	
	private BigDecimal saldoPendente;

	private Boolean pendente;

	private String prot;

	private String responsavelEncaminhamento;
	private String situacaoTotal;
	private String tipoDemanda;
	private String subTipoDemanda;
	private int prazoTotal;
	private int diasRestantesParalisacao;
	
	
	@ManyToOne
	private UnidadeCaixa unidadeCaixa;
	
	@ManyToOne
	private Empreendimento empreendimento;
	
	@ManyToOne
	private FuncionarioCaixa operacional;
	
	@ManyToOne
	private FuncionarioCaixa operacionalAtual;
	
	@ManyToOne
	private FuncionarioCaixa engenheiro;
	
	@ManyToOne
	private FuncionarioCaixa engenheiroAtual;

	@ManyToOne
	private FuncionarioCaixa social;
	
	@ManyToOne
	private FuncionarioCaixa socialAtual;
	
	@ManyToOne
	private FuncionarioCaixa representanteCaixa;

	@ManyToOne
	private FuncionarioCaixa representanteCaixaAtual;
		
	@ManyToOne
	private Entidade tomadorOperacao;
	
	@ManyToOne
	private Entidade tomadorPrincipal;
	
	@ManyToOne
	private Entidade agentePromotorOperacao;
	
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Demanda> demandas;
		
	public List<Demanda> getDemandas() {
		return demandas;
	}
	public void setDemandas(List<Demanda> demandas) {
		this.demandas = demandas;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNumeroOperacao() {
		return numeroOperacao;
	}
	public void setNumeroOperacao(long numeroOperacao) {
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
		return solicitouLiberarParcela;
	}
	public void setSolicitouLiberarParcela(String solicitouLiberarParcela) {
		this.solicitouLiberarParcela = solicitouLiberarParcela;
	}
	public String getCondicionanteLiberarParcela() {
		return condicionanteLiberarParcela;
	}
	public void setCondicionanteLiberarParcela(String condicionanteLiberarParcela) {
		this.condicionanteLiberarParcela = condicionanteLiberarParcela;
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
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNomeAgencia() {
		return nomeAgencia;
	}
	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}
	public String getContaCorrente() {
		return contaCorrente;
	}
	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	public String getContaPoupanca() {
		return contaPoupanca;
	}
	public void setContaPoupanca(String contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}
	public String getIdExterna() {
		return idExterna;
	}
	public void setIdExterna(String idExterna) {
		this.idExterna = idExterna;
	}
	public String getPortaria() {
		return portaria;
	}
	public void setPortaria(String portaria) {
		this.portaria = portaria;
	}
	public String getSimplificado() {
		return simplificado;
	}
	public void setSimplificado(String simplificado) {
		this.simplificado = simplificado;
	}
	public String getSr() {
		return sr;
	}
	public void setSr(String sr) {
		this.sr = sr;
	}
	public String getObtv() {
		return obtv;
	}
	public void setObtv(String obtv) {
		this.obtv = obtv;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getGestor() {
		return gestor;
	}
	public void setGestor(String gestor) {
		this.gestor = gestor;
	}
	public String getImpositivo() {
		return impositivo;
	}
	public void setImpositivo(String impositivo) {
		this.impositivo = impositivo;
	}
	public String getParlamentar() {
		return parlamentar;
	}
	public void setParlamentar(String parlamentar) {
		this.parlamentar = parlamentar;
	}
	public String getEtiquetasDaOperacao() {
		return etiquetasDaOperacao;
	}
	public void setEtiquetasDaOperacao(String etiquetasDaOperacao) {
		this.etiquetasDaOperacao = etiquetasDaOperacao;
	}
	public String getUltimoHistorico() {
		return ultimoHistorico;
	}
	public void setUltimoHistorico(String ultimoHistorico) {
		this.ultimoHistorico = ultimoHistorico;
	}
	public String getSituacaoAtualDetalhada() {
		return situacaoAtualDetalhada;
	}
	public void setSituacaoAtualDetalhada(String situacaoAtualDetalhada) {
		this.situacaoAtualDetalhada = situacaoAtualDetalhada;
	}
	public String getAutorAlteracao() {
		return autorAlteracao;
	}
	public void setAutorAlteracao(String autorAlteracao) {
		this.autorAlteracao = autorAlteracao;
	}
	public String getParalisadas() {
		return paralisadas;
	}
	public void setParalisadas(String paralisadas) {
		this.paralisadas = paralisadas;
	}
	public LocalDate getrPrestacaoContas() {
		return rPrestacaoContas;
	}
	public void setrPrestacaoContas(LocalDate rPrestacaoContas) {
		this.rPrestacaoContas = rPrestacaoContas;
	}
	public LocalDate getDataAssinatura() {
		return dataAssinatura;
	}
	public void setDataAssinatura(LocalDate dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}
	public LocalDate getDataVigencia() {
		return dataVigencia;
	}
	public void setDataVigencia(LocalDate dataVigencia) {
		this.dataVigencia = dataVigencia;
	}
	public LocalDate getDataEnvioMandataria() {
		return dataEnvioMandataria;
	}
	public void setDataEnvioMandataria(LocalDate dataEnvioMandataria) {
		this.dataEnvioMandataria = dataEnvioMandataria;
	}
	public LocalDate getDataPublicacaoDOU() {
		return dataPublicacaoDOU;
	}
	public void setDataPublicacaoDOU(LocalDate dataPublicacaoDOU) {
		this.dataPublicacaoDOU = dataPublicacaoDOU;
	}
	public LocalDate getVencimentoSuspensiva() {
		return vencimentoSuspensiva;
	}
	public void setVencimentoSuspensiva(LocalDate vencimentoSuspensiva) {
		this.vencimentoSuspensiva = vencimentoSuspensiva;
	}
	public LocalDate getDataProjetoBasicoSPA() {
		return dataProjetoBasicoSPA;
	}
	public void setDataProjetoBasicoSPA(LocalDate dataProjetoBasicoSPA) {
		this.dataProjetoBasicoSPA = dataProjetoBasicoSPA;
	}
	public LocalDate getDataLAE() {
		return dataLAE;
	}
	public void setDataLAE(LocalDate dataLAE) {
		this.dataLAE = dataLAE;
	}
	public LocalDate getDataAutorizacaoSPA() {
		return dataAutorizacaoSPA;
	}
	public void setDataAutorizacaoSPA(LocalDate dataAutorizacaoSPA) {
		this.dataAutorizacaoSPA = dataAutorizacaoSPA;
	}
	public LocalDate getDataVRPL() {
		return dataVRPL;
	}
	public void setDataVRPL(LocalDate dataVRPL) {
		this.dataVRPL = dataVRPL;
	}
	public LocalDate getDataDeclInícioObraTomador() {
		return dataDeclInícioObraTomador;
	}
	public void setDataDeclInícioObraTomador(LocalDate dataDeclInícioObraTomador) {
		this.dataDeclInícioObraTomador = dataDeclInícioObraTomador;
	}
	public LocalDate getDataAutorizacaoObra() {
		return dataAutorizacaoObra;
	}
	public void setDataAutorizacaoObra(LocalDate dataAutorizacaoObra) {
		this.dataAutorizacaoObra = dataAutorizacaoObra;
	}
	public LocalDate getDataUltimoCredito() {
		return dataUltimoCredito;
	}
	public void setDataUltimoCredito(LocalDate dataUltimoCredito) {
		this.dataUltimoCredito = dataUltimoCredito;
	}
	public LocalDate getDataUltimaVistoria() {
		return dataUltimaVistoria;
	}
	public void setDataUltimaVistoria(LocalDate dataUltimaVistoria) {
		this.dataUltimaVistoria = dataUltimaVistoria;
	}
	public LocalDate getDataDeDesbloqueio() {
		return dataDeDesbloqueio;
	}
	public void setDataDeDesbloqueio(LocalDate dataDeDesbloqueio) {
		this.dataDeDesbloqueio = dataDeDesbloqueio;
	}
	public LocalDate getDataAprovacaoPrestContasCaixa() {
		return dataAprovacaoPrestContasCaixa;
	}
	public void setDataAprovacaoPrestContasCaixa(LocalDate dataAprovacaoPrestContasCaixa) {
		this.dataAprovacaoPrestContasCaixa = dataAprovacaoPrestContasCaixa;
	}
	public LocalDate getDataUltimoHistorico() {
		return dataUltimoHistorico;
	}
	public void setDataUltimoHistorico(LocalDate dataUltimoHistorico) {
		this.dataUltimoHistorico = dataUltimoHistorico;
	}
	public LocalDate getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(LocalDate dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public LocalDate getDataAio() {
		return dataAio;
	}
	public void setDataAio(LocalDate dataAio) {
		this.dataAio = dataAio;
	}
	public LocalDate getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDate getDataEncaminhamento() {
		return dataEncaminhamento;
	}
	public void setDataEncaminhamento(LocalDate dataEncaminhamento) {
		this.dataEncaminhamento = dataEncaminhamento;
	}
	public BigDecimal getSaldoPendente() {
		return saldoPendente;
	}
	public void setSaldoPendente(BigDecimal saldoPendente) {
		this.saldoPendente = saldoPendente;
	}
	public Boolean getPendente() {
		return pendente;
	}
	public void setPendente(Boolean pendente) {
		this.pendente = pendente;
	}
	public String getProt() {
		return prot;
	}
	public void setProt(String prot) {
		this.prot = prot;
	}
	public String getResponsavelEncaminhamento() {
		return responsavelEncaminhamento;
	}
	public void setResponsavelEncaminhamento(String responsavelEncaminhamento) {
		this.responsavelEncaminhamento = responsavelEncaminhamento;
	}
	public String getSituacaoTotal() {
		return situacaoTotal;
	}
	public void setSituacaoTotal(String situacaoTotal) {
		this.situacaoTotal = situacaoTotal;
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
	public int getPrazoTotal() {
		return prazoTotal;
	}
	public void setPrazoTotal(int prazoTotal) {
		this.prazoTotal = prazoTotal;
	}
	public int getDiasRestantesParalisacao() {
		return diasRestantesParalisacao;
	}
	public void setDiasRestantesParalisacao(int diasRestantesParalisacao) {
		this.diasRestantesParalisacao = diasRestantesParalisacao;
	}

	
}
