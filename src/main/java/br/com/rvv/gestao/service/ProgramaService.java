package br.com.rvv.gestao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.model.Programa;
import br.com.rvv.gestao.repository.ProgramaRepository;

@Service
public class ProgramaService {
	
	@Autowired
	private ProgramaRepository programaRepository;
	
	public Programa getPrograma(String nomePrograma, String nomeObjetivo) {
		
		Optional<Programa> programaBuscado = programaRepository.findByNome(nomePrograma);

		if (!programaBuscado.isPresent()) {
			Programa programa = new Programa(nomePrograma, nomeObjetivo);					
			programaRepository.save(programa);
			return programa;
		} else {
			return programaBuscado.get();
		}
	}

}
