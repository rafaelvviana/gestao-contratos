package br.com.rvv.gestao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rvv.gestao.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
	
	Optional<Contrato> findByNumeroOperacao(int numeroOperacao);

}
