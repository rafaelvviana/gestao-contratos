package br.com.rvv.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rvv.gestao.model.Contrato;
import br.com.rvv.gestao.service.ContratoService;

@Controller
@RequestMapping("contratos")
public class ContratosController {
	
	@Autowired
	private ContratoService contratoService;
			
	@GetMapping("/{lista}")
	public String listaDeContratos(Model model, @PathVariable String lista) {
		
		List<Contrato> listaContratos = contratoService.listaDeContratosDaRegra(lista);
		model.addAttribute("listaContratos", listaContratos);
		return "contrato/listaTodosContratos";
	}
}
