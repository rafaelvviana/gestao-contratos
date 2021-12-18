package br.com.rvv.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rvv.gestao.controller.dto.ResumoContratosDto;
import br.com.rvv.gestao.controller.dto.ResumoDemandasDto;
import br.com.rvv.gestao.controller.dto.UploadLogDto;
import br.com.rvv.gestao.model.UnidadeCaixa;
import br.com.rvv.gestao.model.enums.PaginasEnum;
import br.com.rvv.gestao.service.ContratoService;
import br.com.rvv.gestao.service.DemandaService;
import br.com.rvv.gestao.service.ReadAndUpdateService;
import br.com.rvv.gestao.service.UnidadeCaixaService;

@Controller
public class GestaoController {
	
	long unidade_inicial = 1;
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private UnidadeCaixaService unidadeCaixaService;
	
	@Autowired
	private DemandaService demandaService;
	
	@Autowired
	private ReadAndUpdateService updateService;
	
	private UnidadeCaixa unidade = null;
	private ResumoDemandasDto resumoDemandas =  null;		
	private List<ResumoContratosDto> listaResumoContrato = null;
	private List<UploadLogDto> uploadLog = null;
	
	private String inicia(long idUnidade) {		
		try {						
			this.unidade = unidadeCaixaService.getUnidadeCaixa(idUnidade);
			if(this.unidade == null) {
				return PaginasEnum.SEM_DADOS.getDescricao();
			}			
			this.listaResumoContrato = contratoService.listaResumoContratos(idUnidade);			
			this.resumoDemandas =  demandaService.getResumoDemandas(idUnidade);		
			this.uploadLog = updateService.getListaUploadLog();
		} catch (Exception e) {
			return PaginasEnum.ERRO.getDescricao();
		}
		
		return PaginasEnum.HOME.getDescricao();
	}
	
	@RequestMapping("/")
	public String homeSemParametro(Model model) {
		
		String retorno = inicia(this.unidade_inicial);

		model.addAttribute("listaUploadLog", this.uploadLog);
		model.addAttribute("resumoDemandas", this.resumoDemandas);
		model.addAttribute("unidadeSelecionada", this.unidade);
		model.addAttribute("resumos", this.listaResumoContrato);
		return retorno;
	}
	
	@RequestMapping("/{id}")	
	public String home(@PathVariable("id") long idUnidade, Model model) {
		
		String retorno = inicia(idUnidade);
		model.addAttribute("listaUploadLog", uploadLog);
		model.addAttribute("resumoDemandas", resumoDemandas);
		model.addAttribute("unidadeSelecionada", unidade);
		model.addAttribute("resumos", listaResumoContrato);
		
		return retorno; 
	}		
}
