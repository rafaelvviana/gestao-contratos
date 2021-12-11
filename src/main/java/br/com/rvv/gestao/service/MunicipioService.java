package br.com.rvv.gestao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.model.Municipio;
import br.com.rvv.gestao.repository.MunicipioRepository;

@Service
public class MunicipioService {

	@Autowired
	private MunicipioRepository municipioRepository;
	
	public Municipio getMunicipio(String nomeMunicipio) {
		
		Optional<Municipio> municipioBuscado = municipioRepository.findByNome(nomeMunicipio);

		if (!municipioBuscado.isPresent()) {
			Municipio municipio = new Municipio(nomeMunicipio);					
			municipioRepository.save(municipio);
			return municipio;
		} else {
			return municipioBuscado.get();
		}
	}
}
