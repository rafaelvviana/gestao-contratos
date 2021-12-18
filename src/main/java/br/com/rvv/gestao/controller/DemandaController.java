package br.com.rvv.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rvv.gestao.model.Demanda;
import br.com.rvv.gestao.model.UnidadeCaixa;
import br.com.rvv.gestao.model.enums.PaginasEnum;
import br.com.rvv.gestao.service.DemandaService;
import br.com.rvv.gestao.service.UnidadeCaixaService;

@Controller
@RequestMapping("/demandas")
public class DemandaController {

	@Autowired
	private DemandaService demandaService;
	
	@Autowired
	private UnidadeCaixaService unidadeCaixaService;
	
	@GetMapping("/{idUnidade}")
	public String listarDemandas(@PathVariable("idUnidade") long idUnidade, Model model) {		
		UnidadeCaixa unidade = unidadeCaixaService.getUnidadeCaixa(idUnidade);
		if(unidade == null) {
			return PaginasEnum.SEM_DADOS.getDescricao();
		}
		List<Demanda> listaDemandas = demandaService.listarDemandasPorUnidade(unidade);		
		model.addAttribute("listaDemandas", listaDemandas);
		model.addAttribute("unidadeSelecionada", unidade);
		return PaginasEnum.DEMANDA_LISTA.getDescricao();
	}
}
