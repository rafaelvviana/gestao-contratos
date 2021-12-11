package br.com.rvv.gestao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contratos")
public class ContratosController {
	
	@GetMapping("todos")
	public String listarTodosContratos() {
		return "contrato/listaTodosContratos";
	}
}
