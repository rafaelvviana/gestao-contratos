package br.com.rvv.gestao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.model.FuncionarioCaixa;
import br.com.rvv.gestao.model.enums.CargoCaixaEnum;
import br.com.rvv.gestao.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public FuncionarioCaixa getFuncionario(String nomeFuncionario, CargoCaixaEnum cargo) {
		
		Optional<FuncionarioCaixa> operacionalBuscado = funcionarioRepository.findByNomeAndCargo(nomeFuncionario, cargo);

		if (!operacionalBuscado.isPresent()) {
			FuncionarioCaixa operacional = new FuncionarioCaixa(nomeFuncionario, cargo);					
			funcionarioRepository.save(operacional);
			return operacional;
		} else {
			return operacionalBuscado.get();
		}
	}
}
