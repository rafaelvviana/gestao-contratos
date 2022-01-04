package br.com.rvv.gestao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rvv.gestao.model.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

	Optional<Municipio> findByNome(String nomeMunicipio);
	
}
