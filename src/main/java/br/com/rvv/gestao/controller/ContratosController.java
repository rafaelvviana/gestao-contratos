package br.com.rvv.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rvv.gestao.model.Contrato;
import br.com.rvv.gestao.model.UnidadeCaixa;
import br.com.rvv.gestao.service.ContratoService;
import br.com.rvv.gestao.service.UnidadeCaixaService;

@Controller
@RequestMapping("contratos")
public class ContratosController {
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private UnidadeCaixaService unidadeCaixaService;
	
	@GetMapping("/{unidade}/{lista}")
	public String listaDeContratos(Model model, @PathVariable("unidade") long idUnidade, @PathVariable("lista") String lista) {
		
		UnidadeCaixa unidade = null;
		List<Contrato> listaContratos = null;
		try {
			unidade = unidadeCaixaService.getUnidadeCaixa(idUnidade);		
			if(unidade == null) {
				return "pages/sem-dados.html";
			}
			listaContratos = contratoService.listaDeContratosDaRegra(lista, idUnidade);
		} catch (Exception e) {
			return "pages/erro-inesperado.html";
		}
		
		model.addAttribute("unidadeSelecionada", unidade);
		model.addAttribute("listaContratos", listaContratos);		
		model.addAttribute("nomeLista", contratoService.obterNomeDaRegraDaUrl(lista));
		
		return "contrato/listaTodosContratos";
	}
	
	@GetMapping("/detalhes/{id}") 
	public String detalhesContrato(@PathVariable("id") int id, Model model) {
		
		Contrato contrato = null;
		UnidadeCaixa unidade = null;
		try {
			contrato = contratoService.getContratoPorId(id);
			unidade = contrato.getUnidadeCaixa();
		} catch (Exception e) {
			return "pages/erro-inesperado.html";
		} 		
		
		model.addAttribute("unidadeSelecionada", unidade);
		model.addAttribute("contrato", contrato);
		return "contrato/detalhesContrato.html";
	}
}
