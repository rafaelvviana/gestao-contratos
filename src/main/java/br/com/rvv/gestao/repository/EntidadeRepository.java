package br.com.rvv.gestao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rvv.gestao.model.Entidade;

public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

	Optional<Entidade> findByCnpj(String cnpj);
}
