package br.com.rvv.gestao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.controller.dto.ContratoLidoDto;
import br.com.rvv.gestao.controller.dto.ResumoContratosDto;
import br.com.rvv.gestao.helpers.ConverterHelper;
import br.com.rvv.gestao.model.Contrato;
import br.com.rvv.gestao.model.enums.CargoCaixaEnum;
import br.com.rvv.gestao.repository.ContratoRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;

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

	public ResumoContratosDto resumoContratos() {
		
		ResumoContratosDto resumo = new ResumoContratosDto();
		resumo.quantidadeTotalContratos = 25; // contratoRepository.findAll().size();
		
		resumo.quantidadeParalisadas = 10;
		resumo.percentualParalisadas = Math.round(resumo.quantidadeParalisadas / resumo.quantidadeTotalContratos) * 100;
		
		resumo.quantidadePcf = 15;
		resumo.percentualPcf = Math.round(resumo.quantidadePcf / resumo.quantidadeTotalContratos) * 100;
		return resumo;
	}
	
	public void saveContrato(ContratoLidoDto contratoLido) {
		contratoRepository.save(converterDtoParaContrato(contratoLido));
	}

	private Contrato converterDtoParaContrato(ContratoLidoDto contratoLidoDto) {

		Contrato novoContrato;

		try {
			Optional<Contrato> contrato = contratoRepository
					.findByNumeroOperacao(Integer.parseInt(contratoLidoDto.numeroOperacao));
			
			novoContrato = (contrato.isPresent() ? contrato.get() : new Contrato());
			
		} catch (NumberFormatException e) {
			System.out.println("Erro ao buscar a operação!" + contratoLidoDto.numeroOperacao);
			novoContrato = new Contrato();
		}

		novoContrato.setNumeroOperacao(Integer.parseInt(contratoLidoDto.numeroOperacao));
		novoContrato.setDvOperacao(Integer.parseInt(contratoLidoDto.dvOperacao));
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

		novoContrato.setUnidadeCaixa(unidadeCaixaService.getunidadeCaixa(contratoLidoDto.unidadeCaixa));

		novoContrato.setEmpreendimento(empreendimentoService.getempreendimento(contratoLidoDto.empreendimento,
				contratoLidoDto.objetoEmpreendimento, contratoLidoDto.localidadeEmpreendimento,
				contratoLidoDto.logradouroEmpreendimento,
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

}
