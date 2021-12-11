package br.com.rvv.gestao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.model.Entidade;
import br.com.rvv.gestao.repository.EntidadeRepository;

@Service
public class EntidadeService {

	@Autowired
	private EntidadeRepository entidadeRepository;
	
	public Entidade getEntidade(String nomeEntidade, String cnpjEntidade) {
		
		Optional<Entidade> entidadeBuscada = entidadeRepository.findByCnpj(cnpjEntidade);

		if (!entidadeBuscada.isPresent()) {
			Entidade entidade = new Entidade(nomeEntidade, cnpjEntidade);					
			entidadeRepository.save(entidade);
			return entidade;
		} else {
			return entidadeBuscada.get();
		}
	}
}
