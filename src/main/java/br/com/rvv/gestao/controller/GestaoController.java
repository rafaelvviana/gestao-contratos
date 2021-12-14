package br.com.rvv.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rvv.gestao.controller.dto.ResumoContratosDto;
import br.com.rvv.gestao.model.UnidadeCaixa;
import br.com.rvv.gestao.service.ContratoService;
import br.com.rvv.gestao.service.UnidadeCaixaService;

@Controller
public class GestaoController {

	@Autowired
	private UnidadeCaixaService unidadeCaixaService;
	
	@Autowired
	private ContratoService contratoService;
	
	@RequestMapping("/")	
	public String home(Model model) {
		
		List<UnidadeCaixa> listaUnidades = unidadeCaixaService.listaUnidades();		
		List<ResumoContratosDto> listaResumoContrato = null;
		try {
			listaResumoContrato = contratoService.listaResumoContratos();
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("unidades", listaUnidades);
		model.addAttribute("resumos", listaResumoContrato);
		return "home"; 
	}
	
}
