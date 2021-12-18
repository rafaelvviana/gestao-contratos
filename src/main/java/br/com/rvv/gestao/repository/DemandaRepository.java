package br.com.rvv.gestao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rvv.gestao.model.Demanda;
import br.com.rvv.gestao.model.UnidadeCaixa;

public interface DemandaRepository extends JpaRepository<Demanda, Long> {

	Optional<Demanda> findByProtocolo(String protocolo);
	
	List<Demanda> findByUnidade(UnidadeCaixa unidade);

}
