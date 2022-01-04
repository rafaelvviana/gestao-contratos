package br.com.rvv.gestao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rvv.gestao.model.UnidadeCaixa;

public interface UnidadeCaixaRepository extends JpaRepository<UnidadeCaixa, Long> {
	
	Optional<UnidadeCaixa> findByNome(String nome);

}
