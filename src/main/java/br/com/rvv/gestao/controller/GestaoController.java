package br.com.rvv.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rvv.gestao.controller.dto.ResumoContratosDto;
import br.com.rvv.gestao.model.UnidadeCaixa;
import br.com.rvv.gestao.repository.UnidadeCaixaRepository;
import br.com.rvv.gestao.service.ContratoService;

@Controller
public class GestaoController {

	@Autowired
	private UnidadeCaixaRepository unidadeCaixaRepository;
	
	@Autowired
	private ContratoService contratoService;
	
	@RequestMapping("/")	
	public String home(Model model) {
		
		List<UnidadeCaixa> listaUnidades = unidadeCaixaRepository.findAll();		
		ResumoContratosDto resumo = contratoService.resumoContratos();
		
		model.addAttribute("unidades", listaUnidades);
		model.addAttribute("resumo", resumo);
		return "home"; 
	}
	
}
