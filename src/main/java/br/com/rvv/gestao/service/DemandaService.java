package br.com.rvv.gestao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.controller.dto.DemandaDto;
import br.com.rvv.gestao.controller.dto.ResumoDemandasDto;
import br.com.rvv.gestao.helpers.ConverterHelper;
import br.com.rvv.gestao.model.Demanda;
import br.com.rvv.gestao.model.UnidadeCaixa;
import br.com.rvv.gestao.repository.DemandaRepository;

@Service
public class DemandaService {

	@Autowired
	private DemandaRepository demandaRepository;
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired 
	private UnidadeCaixaService unidadeCaixaService;
	
	public ResumoDemandasDto getResumoDemandas(long idUnidade) {		
		return new ResumoDemandasDto("Demandas",
				demandaRepository.findByUnidade(unidadeCaixaService.getUnidadeCaixa(idUnidade)).size());
	}	
	
	public List<Demanda> listarDemandasPorUnidade(UnidadeCaixa unidade) {		
		return demandaRepository.findByUnidade(unidade);
	}
	
	public void saveDemanda(DemandaDto demandaLida, long idUnidade) {
		demandaRepository.save(converterDtoParaDemanda(demandaLida, idUnidade));
	}

	private Demanda converterDtoParaDemanda(DemandaDto demandaLida, long idUnidade) {
		
		Demanda novaDemanda;		
		try {
			if(demandaLida.protocolo != null) {
				Optional<Demanda> demanda = demandaRepository.findByProtocolo(demandaLida.protocolo);				
				novaDemanda = (demanda.isPresent() ? demanda.get() : new Demanda());
			} else {
				novaDemanda = new Demanda();				
			}
		} catch (NumberFormatException e) {
			System.out.println("Erro ao buscar a demanda!" + demandaLida.numeroOperacao);
			novaDemanda = new Demanda();
		}
		
		if ((demandaLida.numeroOperacao != null) && (demandaLida.numeroOperacao != "")) {
			novaDemanda.setContrato(contratoService.getContratoPorNumeroOperacao(
					ConverterHelper.valorLong(demandaLida.numeroOperacao.substring(0, 7))));						
		}
		
		if(novaDemanda.getContrato() != null) {
			novaDemanda.setUnidade(novaDemanda.getContrato().getUnidadeCaixa());			
		} else {
			novaDemanda.setUnidade(unidadeCaixaService.getUnidadeCaixa(idUnidade));
		}
		
		novaDemanda.setDataEncaminhamento(ConverterHelper.dataLocalDate(demandaLida.dataEncaminhamento));
		novaDemanda.setDataEntrada(ConverterHelper.dataLocalDate(demandaLida.dataEntrada));
		novaDemanda.setTempoExecucao(demandaLida.tempoExecucao);
		novaDemanda.setTempoDeslocamento(demandaLida.tempoDeslocamento);
		novaDemanda.setProtocolo(demandaLida.protocolo);
		novaDemanda.setTipoProcolo(demandaLida.tipoProtocolo);
		novaDemanda.setDocumento(demandaLida.documento);
		novaDemanda.setTipoDocumento(demandaLida.tipoDocumento);
		novaDemanda.setTomadorNome(demandaLida.tomadorNome);
		novaDemanda.setTipoDemanda(demandaLida.tipoDemanda);
		novaDemanda.setSubTipoDemanda(demandaLida.subTipoDemanda);
		novaDemanda.setOrigem(demandaLida.origem);
		novaDemanda.setAssunto(demandaLida.assunto);
		novaDemanda.setSituacao(demandaLida.situacao);
		novaDemanda.setExterno(demandaLida.externo);		
		novaDemanda.setDataEncaminhamentoExterno(ConverterHelper.dataLocalDate(demandaLida.dataEncaminhamentoExterno));
		novaDemanda.setObsExterno(demandaLida.obsExterno);
		novaDemanda.setSituacaoSubTipo(demandaLida.situacaoSubTipo);
		novaDemanda.setPrazoSubTipo(demandaLida.prazoSubTipo);
		novaDemanda.setSituacaoTotal(demandaLida.situacaoTotal);
		novaDemanda.setPrazoTotal(ConverterHelper.integer(demandaLida.prazoTotal));
		novaDemanda.setResponsavel(demandaLida.responsavel);
		novaDemanda.setEquipe(demandaLida.equipe);
				
		return novaDemanda;
	}
	
}
