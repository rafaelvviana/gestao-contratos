package br.com.rvv.gestao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rvv.gestao.model.FuncionarioCaixa;
import br.com.rvv.gestao.model.enums.CargoCaixaEnum;

public interface FuncionarioRepository extends JpaRepository<FuncionarioCaixa, Long> {

	Optional<FuncionarioCaixa> findByNomeAndCargo(String nomeFuncionario, CargoCaixaEnum cargo);
}
