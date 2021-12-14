package br.com.rvv.gestao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rvv.gestao.model.Regra;

public interface RegraRepository extends JpaRepository<Regra, Long> {

	Optional<Regra> findByUrl(String url);
}
