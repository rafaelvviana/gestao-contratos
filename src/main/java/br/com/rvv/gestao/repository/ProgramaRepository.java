package br.com.rvv.gestao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rvv.gestao.model.Programa;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {

	Optional<Programa> findByNome(String nomePrograma);

}
