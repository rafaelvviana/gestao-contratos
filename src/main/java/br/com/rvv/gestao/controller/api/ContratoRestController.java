package br.com.rvv.gestao.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rvv.gestao.model.Contrato;
import br.com.rvv.gestao.repository.ContratoRepository;

@RestController
@RequestMapping("contratos")
public class ContratoRestController {

	@Autowired
	private ContratoRepository contratoRepository;
	
	@GetMapping
	public List<Contrato> listaTodosContratos() {
		return contratoRepository.findAll();
	}
	
}
