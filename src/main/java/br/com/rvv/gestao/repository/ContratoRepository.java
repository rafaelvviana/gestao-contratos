package br.com.rvv.gestao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rvv.gestao.model.Contrato;
import br.com.rvv.gestao.model.UnidadeCaixa;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

	Optional<Contrato> findByNumeroOperacao(long numeroOperacao);
	
	List<Contrato> findByUnidadeCaixa(UnidadeCaixa unidade);

	@Query(value = "SELECT c.* FROM contrato c "
			+ "WHERE c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> todosContratosPorUnidade(@Param (value="unidade") long idUnidade);
	
	@Query(value = "SELECT c.* FROM contrato c WHERE "
			+ "((c.situacaoct = 'CONTRATADA COM CLAUSULA SUSPENSIVA') "
			+ "or (c.situacaoct = 'SOB LIMINAR E CLAUSULA SUSPENSIVA')) "
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosComSuspensiva(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.vr_desbloqueado = 0 and "
			+ "(( c.situacaoct = 'EM SITUACAO NORMAL') "
			+ "or (c.situacaoct = 'LIMINAR JUDICIAL - RESTRICAO CADASTRAL')) "
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosSemDesbloqueio(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.dataVRPL is null "
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosSemDesbloqueioAguardandoProcessoLicitatorio(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.dataVRPL is not null "
			+ "and c.vr_creditado = 0 "
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosSemDesbloqueioAguardandoCreditoRecurso(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.perc_obra_executado = 0" + " AND c.impositivo = 'NÃO' "
			+ " AND c.gestor <> 'MS'" + " AND c.data_envio_mandataria IS NOT NULL"
			+ " AND YEAR(c.data_envio_mandataria) = (YEAR(NOW(1)) - 2)"
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosRap(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.dias_corridos_ultimo_desbloqueio > 90 "
			+ "AND c.vr_desbloqueado > 0" + "AND c.situacao_obra <> 'Concluída'"
			+ "AND c.situacao_obra <> 'Obra física concluída'"
			+ "AND (c.situacaoct = 'EM SITUACAO NORMAL' OR c.situacaoct = 'LIMINAR JUDICIAL - RESTRICAO CADASTRAL')"
			+ "AND (CURRENT_DATE - c.data_vigencia) <= 60"
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosObraParalisada(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.situacaoct <> 'TCE - TOMADAS DE CONTAS ESPECIAIS'"
			+ "AND ((c.vr_necessidade_financeira = 0 AND (c.situacaoct = 'EM SITUACAO NORMAL' OR c.situacaoct = 'LIMINAR JUDICIAL - RESTRICAO CADASTRAL'))"
			+ "	 	OR (c.data_vigencia IS NOT NULL AND (CURRENT_DATE - c.data_vigencia) <= 60))"
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosPcf(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.situacao_obra <> 'Concluída'"
			+ "AND c.situacao_obra <> 'Obra física concluída'"
			+ "AND c.situacaoct <> 'TCE - TOMADAS DE CONTAS ESPECIAIS'" + "AND c.situacaoct <> 'Em Estudo' "
			+ "AND (CURRENT_DATE - c.data_vigencia) <= 60"
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosComVigenciaVencida(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.situacaoct = 'TCE - TOMADAS DE CONTAS ESPECIAIS' "
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosTce(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.situacaoct = 'Em Estudo' "
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosNaoContratada(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.situacaocauc = 'Sim'"
			+ "AND c.vr_creditado = 0 ", nativeQuery = true)
	List<Contrato> contratosCrCelebradoComInadimplencia(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.saldo_conta_corrente > 0 AND c.saldo_aplicacao > 0 "
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosSaldoContas(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE (CURRENT_DATE - c.data_ultima_alteracao) > 30 "
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosDesatualizadoMais30Dias(@Param (value="unidade") long idUnidade);

	@Query(value = "SELECT c.* FROM contrato c WHERE c.dias_restantes_paralisacao > 0 AND c.dias_restantes_paralisacao < 45 "
			+ "and c.unidade_caixa_id = :unidade ", nativeQuery = true)
	List<Contrato> contratosOperacaoComMenos45DiasParalisacao(@Param (value="unidade") long idUnidade);
}
