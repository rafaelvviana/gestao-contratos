package br.com.rvv.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.rvv.gestao.model.UnidadeCaixa;
import br.com.rvv.gestao.repository.UnidadeCaixaRepository;

@ControllerAdvice
public class UnidadeController {

	@Autowired
	private UnidadeCaixaRepository unidadeCaixaRepository;
	
	@ModelAttribute("unidades")
	public List<UnidadeCaixa> obterTodasUnidade() {
	   return unidadeCaixaRepository.findAll();
	}
}
