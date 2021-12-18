package br.com.rvv.gestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.rvv.gestao.controller.dto.UploadLogProjecaoDto;
import br.com.rvv.gestao.model.UploadLog;

public interface UploadLogRepository extends JpaRepository<UploadLog, Long> {

	@Query(value="SELECT u.tipo, MAX(u.data) data FROM upload_log u GROUP BY u.tipo",
			nativeQuery = true)
	List<UploadLogProjecaoDto> getUltimaAtualizacoes();
}
