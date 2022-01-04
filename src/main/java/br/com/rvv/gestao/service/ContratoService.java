package br.com.rvv.gestao.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.controller.dto.ContratoDto;
import br.com.rvv.gestao.controller.dto.ResumoContratosDto;
import br.com.rvv.gestao.helpers.ConverterHelper;
import br.com.rvv.gestao.model.Contrato;
import br.com.rvv.gestao.model.Regra;
import br.com.rvv.gestao.model.UnidadeCaixa;
import br.com.rvv.gestao.model.enums.CargoCaixaEnum;
import br.com.rvv.gestao.repository.ContratoRepository;
import br.com.rvv.gestao.repository.RegraRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private RegraRepository regraRepository;

	
	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private MunicipioService municipioService;

	@Autowired
	private ProgramaService programaService;

	@Autowired
	private EmpreendimentoService empreendimentoService;

	@Autowired
	private UnidadeCaixaService unidadeCaixaService;

	@Autowired
	private EntidadeService tomadorService;

	private Contrato getContratoFromOptional(Optional<Contrato> contrato) {		
		if(contrato.isPresent()) {
			return contrato.get();
		}
		return null;
	}

	public Contrato getContratoPorId(long id) {
		return getContratoFromOptional(contratoRepository.findById(id));
	}
	
	public Contrato getContratoPorNumeroOperacao(long numeroOperacao) {
		return getContratoFromOptional(contratoRepository.findByNumeroOperacao(numeroOperacao));
	}
	
	public long totalContratos() {
		return contratoRepository.count();
	}
	
	public List<Contrato> getTodosContratosPorUnidade(UnidadeCaixa unidade) {
		return contratoRepository.findByUnidadeCaixa(unidade);
	}
	
	public String obterNomeDaRegraDaUrl(String urlRegra) {
		Optional<Regra> regra = regraRepository.findByUrl(urlRegra);

		if (regra.isPresent()) {
			return regra.get().getDescricao();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Contrato> listaDeContratosDaRegra(String urlRegra, long idUnidade) {

		Optional<Regra> regra = regraRepository.findByUrl(urlRegra);

		if (regra.isPresent()) {
			try {
				Method metodo = contratoRepository.getClass().getMethod(regra.get().getMethod(), long.class);
				return (List<Contrato>) metodo.invoke(contratoRepository, idUnidade);

			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {

				e.printStackTrace();
			}
		}
		return null;
	}
		
	@SuppressWarnings("unchecked")
	public List<ResumoContratosDto> listaResumoContratos(long idUnidade) throws NoSuchMethodException, SecurityException {

		/**
		 * TODO
		 * Código deve ser removido.
		 * Inserir dados no banco
		 */
		if (regraRepository.count() == 0) {
			this.inicializarRegras();
		}
		
		List<ResumoContratosDto> lista = new ArrayList<ResumoContratosDto>();
		int quantidadeTodos = (int) totalContratos();

		regraRepository.findAll().forEach(regra -> {
			try {
				Method metodo = contratoRepository.getClass().getMethod(regra.getMethod(), long.class);
				int quantidade = ((List<Contrato>) metodo.invoke(contratoRepository, idUnidade)).size();
				if(quantidade > 0) {
					long percentual = (quantidade > 0 ? Math.round((float) quantidade / quantidadeTodos * 100) : 0);
					ResumoContratosDto resumo = new ResumoContratosDto(regra, quantidade, percentual);
					lista.add(resumo);					
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {

				e.printStackTrace();
			}
		});

		return lista;
	}

	public void saveContrato(ContratoDto contratoLido) {
		contratoRepository.save(converterDtoParaContrato(contratoLido));
	}

	private Contrato converterDtoParaContrato(ContratoDto contratoLidoDto) {

		Contrato novoContrato;

		try {
			Optional<Contrato> contrato = contratoRepository
					.findByNumeroOperacao(Integer.parseInt(contratoLidoDto.numeroOperacao));

			novoContrato = (contrato.isPresent() ? contrato.get() : new Contrato());

		} catch (NumberFormatException e) {
			System.out.println("Erro ao buscar a operação!" + contratoLidoDto.numeroOperacao);
			novoContrato = new Contrato();
		}

		novoContrato.setNumeroOperacao(ConverterHelper.valorLong(contratoLidoDto.numeroOperacao));
		novoContrato.setDvOperacao(ConverterHelper.integer(contratoLidoDto.dvOperacao));
		novoContrato.setNumeroSiconv(ConverterHelper.valorLong(contratoLidoDto.numeroSiconv));
		novoContrato.setNumeroPropostaSiconv(ConverterHelper.valorLong(contratoLidoDto.numeroPropostaSiconv));
		novoContrato.setTipoProjeto(contratoLidoDto.tipoProjeto);

		novoContrato.setSituacaoCAUC(contratoLidoDto.situacaoCAUC);
		novoContrato.setSituacaoCT(contratoLidoDto.situacaoCT);
		novoContrato.setEstagioCT(contratoLidoDto.estagioCT);
		novoContrato.setSituacaoConvenioSICONV(contratoLidoDto.situacaoConvenioSICONV);
		novoContrato.setPermiteAdiantarParcela(contratoLidoDto.permiteAdiantarParcela);
		novoContrato.setSolicitouLiberarParcela(contratoLidoDto.SolicitouLiberarParcela);
		novoContrato.setCondicionanteLiberarParcela(contratoLidoDto.condicionanteLiberarParcela);
		novoContrato.setDescricaoCondicionante(contratoLidoDto.descricaoCondicionante);
		novoContrato.setSituacaoProjetoBasicoSPA(contratoLidoDto.situacaoProjetoBasicoSPA);
		novoContrato.setLicitacao(contratoLidoDto.licitacao);

		novoContrato.setDiasCorridosPrimeiroCredito(ConverterHelper.valorLong(contratoLidoDto.diasCorridosSemVRPL));
		novoContrato.setVi(ConverterHelper.bigDecimal(contratoLidoDto.vi));
		novoContrato.setVr(ConverterHelper.bigDecimal(contratoLidoDto.vr));
		novoContrato.setCp1(ConverterHelper.bigDecimal(contratoLidoDto.cp1));
		novoContrato.setCp2(ConverterHelper.bigDecimal(contratoLidoDto.cp2));
		novoContrato.setVrEmpenhado(ConverterHelper.bigDecimal(contratoLidoDto.vrEmpenhado));
		novoContrato.setVrCreditado(ConverterHelper.bigDecimal(contratoLidoDto.vrCreditado));
		novoContrato.setVrSolicitado(ConverterHelper.bigDecimal(contratoLidoDto.vrSolicitado));
		novoContrato.setVrNecessidadeFinanceira(ConverterHelper.bigDecimal(contratoLidoDto.vrNecessidadeFinanceira));
		novoContrato.setVrDesbloqueado(ConverterHelper.bigDecimal(contratoLidoDto.vrDesbloqueado));
		novoContrato.setVrSaldoCreditado(ConverterHelper.bigDecimal(contratoLidoDto.vrSaldoCreditado));
		novoContrato.setCpCreditada(ConverterHelper.bigDecimal(contratoLidoDto.cpCreditada));
		novoContrato.setCpDesbloqueado(ConverterHelper.bigDecimal(contratoLidoDto.cpDesbloqueado));
		novoContrato.setViPTSRepasse(ConverterHelper.bigDecimal(contratoLidoDto.viPTSRepasse));
		novoContrato.setViPTSContrapartida(ConverterHelper.bigDecimal(contratoLidoDto.viPTSContrapartida));
		novoContrato.setViPTSDesbloqueado(ConverterHelper.bigDecimal(contratoLidoDto.viPTSDesbloqueado));
		novoContrato.setViQCIVigente(ConverterHelper.bigDecimal(contratoLidoDto.viQCIVigente));
		novoContrato.setPercViDesbloqueado(ConverterHelper.bigDecimal(contratoLidoDto.percViDesbloqueado));
		novoContrato.setPercViQCIDesbloqueado(ConverterHelper.bigDecimal(contratoLidoDto.percViQCIDesbloqueado));
		novoContrato.setPercObraInformado(ConverterHelper.bigDecimal(contratoLidoDto.percObraInformado));
		novoContrato.setPercObraExecutado(ConverterHelper.bigDecimal(contratoLidoDto.percObraExecutado));
		novoContrato.setPercObraLiberacao(ConverterHelper.bigDecimal(contratoLidoDto.percObraLiberacao));
		novoContrato.setSituacaoObra(contratoLidoDto.situacaoObra);
		novoContrato.setSaldoContaCorrente(ConverterHelper.bigDecimal(contratoLidoDto.saldoContaCorrente));
		novoContrato.setSaldoAplicacao(ConverterHelper.bigDecimal(contratoLidoDto.saldoAplicacao));
		novoContrato.setSaldoContaPoupanca(ConverterHelper.bigDecimal(contratoLidoDto.saldoContaPoupanca));
//		novoContrato.setSaldoGeral(ConverterHelper.bigDecimal(contratoLidoDto.saldogeral);			
		novoContrato.setDisponivelPagamentoImediato(
				ConverterHelper.bigDecimal(contratoLidoDto.disponivelPagamentoImediato));
		novoContrato
				.setDiasCorridosPrimeiroCredito(ConverterHelper.valorLong(contratoLidoDto.diasCorridosPrimeiroCredito));
		novoContrato.setDiasCorridosUltimoDesbloqueio(
				ConverterHelper.valorLong(contratoLidoDto.diasCorridosUltimoDesbloqueio));
		novoContrato.setPotencialPagamentoFuturo(ConverterHelper.bigDecimal(contratoLidoDto.potencialPagamentoFuturo));
		novoContrato.setDataPrimeiroCredito(ConverterHelper.dataLocalDate(contratoLidoDto.dataPrimeiroCredito));
		novoContrato.setVrUltimoDesbloqueio(ConverterHelper.bigDecimal(contratoLidoDto.vrUltimoDesbloqueio));
		novoContrato.setSituacaoPrestacaoContas(contratoLidoDto.situacaoPrestacaoContas);

		novoContrato.setAgencia(contratoLidoDto.agencia);
		novoContrato.setNomeAgencia(contratoLidoDto.nomeAgencia);
		novoContrato.setContaCorrente(contratoLidoDto.contaCorrente);
		novoContrato.setContaPoupanca(contratoLidoDto.contaPoupanca);
		novoContrato.setIdExterna(contratoLidoDto.idExterna);
		novoContrato.setPortaria(contratoLidoDto.portaria);
		novoContrato.setSimplificado(contratoLidoDto.simplificado);
		novoContrato.setSr(contratoLidoDto.sr);
		novoContrato.setObtv(contratoLidoDto.obtv);
		novoContrato.setCliente(contratoLidoDto.cliente);
		novoContrato.setGestor(contratoLidoDto.gestor);
		novoContrato.setImpositivo(contratoLidoDto.impositivo);
		novoContrato.setParlamentar(contratoLidoDto.parlamentar);
		novoContrato.setEtiquetasDaOperacao(contratoLidoDto.etiquetasDaOperacao);
		novoContrato.setUltimoHistorico(contratoLidoDto.ultimoHistorico);
		novoContrato.setSituacaoAtualDetalhada(contratoLidoDto.situacaoAtualDetalhada);
		novoContrato.setAutorAlteracao(contratoLidoDto.autorAlteracao);
		novoContrato.setParalisadas(contratoLidoDto.paralisadas);

		novoContrato.setrPrestacaoContas(ConverterHelper.dataLocalDate(contratoLidoDto.rPrestacaoContas));
		novoContrato.setDataAssinatura(ConverterHelper.dataLocalDate(contratoLidoDto.dataAssinatura));
		novoContrato.setDataVigencia(ConverterHelper.dataLocalDate(contratoLidoDto.dataVigencia));

		novoContrato.setDataEnvioMandataria(ConverterHelper.dataLocalDate(contratoLidoDto.dataEnvioMandataria));
		novoContrato.setDataPublicacaoDOU(ConverterHelper.dataLocalDate(contratoLidoDto.dataPublicacaoDOU));
		novoContrato.setVencimentoSuspensiva(ConverterHelper.dataLocalDate(contratoLidoDto.vencimentoSuspensiva));
		novoContrato.setDataProjetoBasicoSPA(ConverterHelper.dataLocalDate(contratoLidoDto.dataProjetoBasicoSPA));
		novoContrato.setDataLAE(ConverterHelper.dataLocalDate(contratoLidoDto.dataLAE));
		novoContrato.setDataAutorizacaoSPA(ConverterHelper.dataLocalDate(contratoLidoDto.dataAutorizacaoSPA));
		novoContrato.setDataVRPL(ConverterHelper.dataLocalDate(contratoLidoDto.dataVRPL));
		novoContrato
				.setDataDeclInícioObraTomador(ConverterHelper.dataLocalDate(contratoLidoDto.dataDeclInícioObraTomador));
		novoContrato.setDataAutorizacaoObra(ConverterHelper.dataLocalDate(contratoLidoDto.dataAutorizacaoObra));
		novoContrato.setDataUltimoCredito(ConverterHelper.dataLocalDate(contratoLidoDto.dataUltimoCredito));
		novoContrato.setDataUltimaVistoria(ConverterHelper.dataLocalDate(contratoLidoDto.dataUltimaVistoria));
		novoContrato.setDataDeDesbloqueio(ConverterHelper.dataLocalDate(contratoLidoDto.dataDeDesbloqueio));
		novoContrato.setDataAprovacaoPrestContasCaixa(
				ConverterHelper.dataLocalDate(contratoLidoDto.dataAprovacaoPrestContasCaixa));
		novoContrato.setDataUltimoHistorico(ConverterHelper.dataLocalDate(contratoLidoDto.dataUltimoHistorico));
		novoContrato.setDataUltimaAlteracao(ConverterHelper.dataLocalDate(contratoLidoDto.dataUltimaAlteracao));

//		novoContrato.setDataAio(ConverterHelper.dataLocalDate(contratoLidoDto.dataAio);
//		novoContrato.setDataEntrada(ConverterHelper.dataLocalDate(contratoLidoDto.dataEntrada);
//		novoContrato.setDataEncaminhamento(ConverterHelper.dataLocalDate(contratoLidoDto.dataEncaminhamento);
//		
//		novoContrato.setSaldoPendente(ConverterHelper.bigDecimal(contratoLidoDto.saldoPendente);

//		novoContrato.setProt(contratoLidoDto.prot);
//		novoContrato.setResponsavelEncaminhamento(contratoLidoDto.responsavelEncaminhamento);
//		novoContrato.setSituacaoTotal(contratoLidoDto.situacaoTotal);
//		novoContrato.setTipoDemanda(contratoLidoDto.tipoDemanda);
//		novoContrato.setSubTipoDemanda(contratoLidoDto.subTipoDemanda);
//		novoContrato.setPrazoTotal(contratoLidoDto.prazoTotal);

		novoContrato.setDiasRestantesParalisacao(ConverterHelper.integer(contratoLidoDto.diasRestantesParalisacao));
		novoContrato.setPendente(false);

		novoContrato.setUnidadeCaixa(unidadeCaixaService.getUnidadeCaixa(contratoLidoDto.unidadeCaixa));

		novoContrato.setEmpreendimento(empreendimentoService.getempreendimento(contratoLidoDto.empreendimento,
				contratoLidoDto.objetoEmpreendimento, contratoLidoDto.localidadeEmpreendimento,
				contratoLidoDto.logradouroEmpreendimento, contratoLidoDto.apelidoEmpreendimento,
				contratoLidoDto.observacaoEmpreendimento,
				ConverterHelper.dataLocalDate(contratoLidoDto.dataSelecaoEmpreendimento),
				municipioService.getMunicipio(contratoLidoDto.municipioEmpreendimento),
				programaService.getPrograma(contratoLidoDto.programa, contratoLidoDto.objetivoPrograma)));

		novoContrato.setOperacional(
				funcionarioService.getFuncionario(contratoLidoDto.operacional, CargoCaixaEnum.OPERACIONAL));
		novoContrato.setOperacionalAtual(
				funcionarioService.getFuncionario(contratoLidoDto.operacionalAtual, CargoCaixaEnum.OPERACIONAL));
		novoContrato.setEngenheiro(
				funcionarioService.getFuncionario(contratoLidoDto.engenheiro, CargoCaixaEnum.ENGENHARIA));
		novoContrato.setEngenheiroAtual(
				funcionarioService.getFuncionario(contratoLidoDto.engenheiroAtual, CargoCaixaEnum.ENGENHARIA));
		novoContrato.setSocial(funcionarioService.getFuncionario(contratoLidoDto.social, CargoCaixaEnum.SOCIAL));
		novoContrato
				.setSocialAtual(funcionarioService.getFuncionario(contratoLidoDto.socialAtual, CargoCaixaEnum.SOCIAL));
		novoContrato.setRepresentanteCaixa(funcionarioService.getFuncionario(contratoLidoDto.representanteCaixa,
				CargoCaixaEnum.REPRESENTANTE_CAIXA));
		novoContrato.setRepresentanteCaixaAtual(funcionarioService
				.getFuncionario(contratoLidoDto.representanteCaixaAtual, CargoCaixaEnum.REPRESENTANTE_CAIXA));
		novoContrato.setTomadorOperacao(
				tomadorService.getEntidade(contratoLidoDto.tomadorNome, contratoLidoDto.tomadorCnpj));
		novoContrato.setAgentePromotorOperacao(
				tomadorService.getEntidade(contratoLidoDto.agentePromotorNome, contratoLidoDto.agentePromotorCnpj));

		return novoContrato;
	}

	public void inicializarRegras() {

		// 1
		Regra regraSuspensiva = new Regra();
		regraSuspensiva.setNome("SUSPENSIVA");
		regraSuspensiva.setDescricao("Com suspensiva");
		regraSuspensiva.setMethod("contratosComSuspensiva");
		regraSuspensiva.setUrl("suspensiva");
		regraRepository.save(regraSuspensiva);
		// 2
		Regra regraSemDesbloqueio = new Regra();
		regraSemDesbloqueio.setNome("SEM_DESBLOQUEIO");
		regraSemDesbloqueio.setDescricao("Sem desbloqueios");
		regraSemDesbloqueio.setMethod("contratosSemDesbloqueio");
		regraSemDesbloqueio.setUrl("semdesbloqueio");
		regraRepository.save(regraSemDesbloqueio);
		// 3
		Regra regraSemDesbloqueioAguardandoProcessoLicitatorio = new Regra();
		regraSemDesbloqueioAguardandoProcessoLicitatorio.setNome("SEM_DESBLOQUEIO_AGUARDANDO_PROCESSO_LICITATORIO");
		regraSemDesbloqueioAguardandoProcessoLicitatorio
				.setDescricao("Aguard. Processo Licitatório");
		regraSemDesbloqueioAguardandoProcessoLicitatorio
				.setMethod("contratosSemDesbloqueioAguardandoProcessoLicitatorio");
		regraSemDesbloqueioAguardandoProcessoLicitatorio.setUrl("semdesbloqueioaguardandolicitatorio");
		regraRepository.save(regraSemDesbloqueioAguardandoProcessoLicitatorio);
		// 4
		Regra regraSemDesbloqueioAguardandoCreditoRecurso = new Regra();
		regraSemDesbloqueioAguardandoCreditoRecurso.setNome("SEM_DESBLOQUEIO_AGUARDANDO_CREDITO_RECURSO");
		regraSemDesbloqueioAguardandoCreditoRecurso.setDescricao("Aguard. Crédito de Recusos");
		regraSemDesbloqueioAguardandoCreditoRecurso.setMethod("contratosSemDesbloqueioAguardandoCreditoRecurso");
		regraSemDesbloqueioAguardandoCreditoRecurso.setUrl("semdesbloqueioaguardandocreditorecurso");
		regraRepository.save(regraSemDesbloqueioAguardandoCreditoRecurso);
		// 5
		Regra regraRap = new Regra();
		regraRap.setNome("RAP");
		regraRap.setDescricao("Restos a Pagar");
		regraRap.setMethod("contratosSemDesbloqueioAguardandoCreditoRecurso");
		regraRap.setUrl("rap");
		regraRepository.save(regraRap);
		// 6
		Regra regraObraParalisada = new Regra();
		regraObraParalisada.setNome("OBRA_PARALISADA");
		regraObraParalisada.setDescricao("Obra Paralisada");
		regraObraParalisada.setMethod("contratosObraParalisada");
		regraObraParalisada.setUrl("obraparalisada");
		regraRepository.save(regraObraParalisada);
		// 7
		Regra regraPcf = new Regra();
		regraPcf.setNome("PCF");
		regraPcf.setDescricao("Prestação de Contas Final");
		regraPcf.setMethod("contratosPcf");
		regraPcf.setUrl("pcf");
		regraRepository.save(regraPcf);
		// 8
		Regra regraVigenciaVencida = new Regra();
		regraVigenciaVencida.setNome("VIGENCIA_VENCIDA");
		regraVigenciaVencida.setDescricao("Vigência vencida");
		regraVigenciaVencida.setMethod("contratosComVigenciaVencida");
		regraVigenciaVencida.setUrl("vigenciavencida");
		regraRepository.save(regraVigenciaVencida);
		// 9
		Regra regraTce = new Regra();
		regraTce.setNome("TCE");
		regraTce.setDescricao("Tomada de Contas Especial");
		regraTce.setMethod("contratosTce");
		regraTce.setUrl("tce");
		regraRepository.save(regraTce);
		// 10
		Regra regraNaoContratada = new Regra();
		regraNaoContratada.setNome("NAO_CONTRATADA");
		regraNaoContratada.setDescricao("Não contratada");
		regraNaoContratada.setMethod("contratosNaoContratada");
		regraNaoContratada.setUrl("naocontratada");
		regraRepository.save(regraNaoContratada);
		// 11
		Regra regraCrCelebradoComInadimplencia = new Regra();
		regraCrCelebradoComInadimplencia.setNome("CR_CELEBRADO_COM_INADIMPLENCIA");
		regraCrCelebradoComInadimplencia.setDescricao("CR Celebrado c/ inadimplência");
		regraCrCelebradoComInadimplencia.setMethod("contratosCrCelebradoComInadimplencia");
		regraCrCelebradoComInadimplencia.setUrl("crcelebradocominadimplencia");
		regraRepository.save(regraCrCelebradoComInadimplencia);
		// 12
		Regra regraSaldoDeContas = new Regra();
		regraSaldoDeContas.setNome("SALDO_DE_CONTAS");
		regraSaldoDeContas.setDescricao("Saldo de contas");
		regraSaldoDeContas.setMethod("contratosSaldoContas");
		regraSaldoDeContas.setUrl("saldocontas");
		regraRepository.save(regraSaldoDeContas);		
		// 13
		Regra regraDesatualizadoMaisDeDias = new Regra();
		regraDesatualizadoMaisDeDias.setNome("DESATUALIZADO_MAIS_30_DIAS");
		regraDesatualizadoMaisDeDias.setDescricao("Desatualizado a mais de 30 dias");
		regraDesatualizadoMaisDeDias.setMethod("contratosDesatualizadoMais30Dias");
		regraDesatualizadoMaisDeDias.setUrl("desatualizadomais30dias");
		regraRepository.save(regraDesatualizadoMaisDeDias);		
		// 14
		Regra regraOperacaoComMenos45DiasParalisacao = new Regra();
		regraOperacaoComMenos45DiasParalisacao.setNome("OPERACAO_COM_MENOS_45_DIAS_PARALISACAO");
		regraOperacaoComMenos45DiasParalisacao.setDescricao("Menos 45 dias paralisação");
		regraOperacaoComMenos45DiasParalisacao.setMethod("contratosOperacaoComMenos45DiasParalisacao");
		regraOperacaoComMenos45DiasParalisacao.setUrl("operacaomenos45idasparalisacao");
		regraRepository.save(regraOperacaoComMenos45DiasParalisacao);		
		
		// ****

		Regra regraTodos = new Regra();
		regraTodos.setNome("TODOS");
		regraTodos.setDescricao("Todos os contratos");
		regraTodos.setMethod("todosContratosPorUnidade");
		regraTodos.setUrl("todos");
		regraRepository.save(regraTodos);
	}

}
