package br.com.rvv.gestao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rvv.gestao.model.Empreendimento;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {

	Optional<Empreendimento> findByNome(String nomeEmpreendimento);

}
