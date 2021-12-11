package br.com.rvv.gestao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.model.Empreendimento;
import br.com.rvv.gestao.model.Municipio;
import br.com.rvv.gestao.model.Programa;
import br.com.rvv.gestao.repository.EmpreendimentoRepository;

@Service
public class EmpreendimentoService {

	@Autowired
	private EmpreendimentoRepository empreendimentoRepository;

	public Empreendimento getempreendimento(String nomeEmpreendimento, String objeto, String localidade,
			String logradouro, Municipio municipio, Programa programa) {

		Optional<Empreendimento> empreendimentoBuscado = empreendimentoRepository.findByNome(nomeEmpreendimento);

		if (!empreendimentoBuscado.isPresent()) {
			Empreendimento empreendimento = new Empreendimento(nomeEmpreendimento, objeto, localidade, logradouro,
					municipio, programa);
			empreendimentoRepository.save(empreendimento);
			return empreendimento;
		} else {
			return empreendimentoBuscado.get();
		}
	}

}
