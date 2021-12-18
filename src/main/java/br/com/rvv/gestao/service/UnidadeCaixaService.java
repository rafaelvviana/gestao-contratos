package br.com.rvv.gestao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.model.UnidadeCaixa;
import br.com.rvv.gestao.repository.UnidadeCaixaRepository;

@Service
public class UnidadeCaixaService {

	@Autowired
	private UnidadeCaixaRepository unidadeCaixaRepository;
	
	public List<UnidadeCaixa> listaUnidades() {
		return unidadeCaixaRepository.findAll();
	}
	
	public UnidadeCaixa getUnidadeCaixa(long idUnidadeCaixa) {
		Optional<UnidadeCaixa> unidade = unidadeCaixaRepository.findById(idUnidadeCaixa);

		if (unidade.isPresent()) {
			return unidade.get();			
		}
		return null;		
	}
	
	public UnidadeCaixa getUnidadeCaixa(String nomeUnidadeCaixa) {
		
		Optional<UnidadeCaixa> unidadeCaixaBuscado = unidadeCaixaRepository.findByNome(nomeUnidadeCaixa);

		if (!unidadeCaixaBuscado.isPresent()) {
			UnidadeCaixa unidadeCaixa = new UnidadeCaixa(nomeUnidadeCaixa);					
			unidadeCaixaRepository.save(unidadeCaixa);
			return unidadeCaixa;
		} else {
			return unidadeCaixaBuscado.get();
		}
	}
	
}
