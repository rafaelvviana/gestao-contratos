package br.com.rvv.gestao.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.rvv.gestao.controller.dto.ContratoDto;
import br.com.rvv.gestao.controller.dto.DemandaDto;
import br.com.rvv.gestao.controller.dto.RegistroImportadoDto;
import br.com.rvv.gestao.controller.dto.UploadLogDto;
import br.com.rvv.gestao.controller.dto.UploadLogProjecaoDto;
import br.com.rvv.gestao.model.UploadLog;
import br.com.rvv.gestao.model.enums.TipoUploadEnum;
import br.com.rvv.gestao.repository.UploadLogRepository;

@Service
public class ReadAndUpdateService {
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private DemandaService demandaService;
		
	@Autowired
	private UploadLogRepository uploadRepository;

	private HashMap<String, Integer> mapCamposRegistroDtoDeParaIndex;
	private HashMap<String, String> mapCamposPlanilhaDeParaContratoDto;
	private HashMap<Integer, String> mapCamposPlanilhaDeParaIndex = new HashMap<Integer, String>();
	private Resource dicionarioContratoDto = new ClassPathResource("files/dicionario.contrato.dto");
	private Resource dicionarioDemandaDto = new ClassPathResource("files/dicionario.demanda.dto");
	
	private String tipoUpload;
	
	private Resource getResourceRegistroImportadoDto() {
		if(this.tipoUpload.equals(TipoUploadEnum.CONTRATO.getDescricao())) {
			return dicionarioContratoDto;
		} else 
		if(this.tipoUpload.equals(TipoUploadEnum.DEMANDAS.getDescricao())) {
			return dicionarioDemandaDto;
		}
		return null;
	}
	
	private void inicializarMapsDeCampos() throws FileNotFoundException, IOException {
		mapCamposRegistroDtoDeParaIndex = getCamposRegistroDtoDeParaIndex();
		mapCamposPlanilhaDeParaContratoDto = getCamposPlanilhaDeParaContratoDto(getResourceRegistroImportadoDto());
	}
	
	private void limparMapsDeCampos() {
		mapCamposPlanilhaDeParaIndex.clear();
		mapCamposRegistroDtoDeParaIndex.clear();
	}
	
	private void getFileHeader(Row row) {		
		row.forEach(celula -> mapCamposPlanilhaDeParaIndex.put(mapCamposPlanilhaDeParaIndex.size(),
				getCellValue(celula).toString().replace(" ", "")));	
	}

	private RegistroImportadoDto getRecordsFromFile(Row row) {
		if(this.tipoUpload.equals(TipoUploadEnum.CONTRATO.getDescricao())) {
			return getRegistroFromRow(row, new ContratoDto());
		} else
		if(this.tipoUpload.equals(TipoUploadEnum.DEMANDAS.getDescricao())) {
			return getRegistroFromRow(row, new DemandaDto());
		}
		return null;
	}
		
	private Sheet getSheetFromFile(FileInputStream arquivo, Integer numSheet) throws IOException {
		try {
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(arquivo);
			return workbook.getSheetAt(0);
		} catch (Exception e) {
			throw new IOException("Arquivo fora do formato esperado.");
		}
	}

	private RegistroImportadoDto getRegistroFromRow(Row row, RegistroImportadoDto registroImportado) {
		
		Field[] fields = registroImportado.getClass().getDeclaredFields();
		row.forEach(celula -> {
			try {
				Integer idCampoNoBanco = getIdCampoRegistroDto(celula.getColumnIndex());
				if (idCampoNoBanco != null) {
					fields[idCampoNoBanco].set(registroImportado, getCellValue(celula).toString());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});

		return registroImportado;
	}

	private Integer getIdCampoRegistroDto(int i) {
		try {
			return mapCamposRegistroDtoDeParaIndex
					.get(mapCamposPlanilhaDeParaContratoDto.get(mapCamposPlanilhaDeParaIndex.get(i)));
		} catch (Exception e) {
			return null;
		}
	}

	private HashMap<String, Integer> getCamposRegistroDtoDeParaIndex()  {
		HashMap<String, Integer> listaCamposRegistroDtoDeParaIndex = new HashMap<String, Integer>();
		
		Field[] campos = null;
		if(this.tipoUpload.equals(TipoUploadEnum.CONTRATO.getDescricao())) {
			campos = ContratoDto.class.getFields();
		} else 
		if(this.tipoUpload.equals(TipoUploadEnum.DEMANDAS.getDescricao())) {
			campos = DemandaDto.class.getFields();
		}
		
		for (Field field : campos) {
			listaCamposRegistroDtoDeParaIndex.put(field.getName(), listaCamposRegistroDtoDeParaIndex.size());
		}
		return listaCamposRegistroDtoDeParaIndex;
	}

	private HashMap<String, String> getCamposPlanilhaDeParaContratoDto(Resource resource) throws FileNotFoundException, IOException {
		HashMap<String, String> listaCamposPlanilhaDeParaContratoDto = new HashMap<String, String>();		
		Scanner scanner = new Scanner(resource.getFile());
		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			@SuppressWarnings("resource")
			Scanner scannerLinha = new Scanner(linha);
			scannerLinha.useDelimiter(",");
			String key = scannerLinha.next();
			String value = scannerLinha.next();
			listaCamposPlanilhaDeParaContratoDto.put(key, value);
		}
		return listaCamposPlanilhaDeParaContratoDto;
	}

	@SuppressWarnings("deprecation")
	private Object getCellValue(Cell celula) {
		Object cellValue = null;

		CellType cellType = celula.getCellTypeEnum();
		if (cellType == CellType.STRING) {
			cellValue = celula.getStringCellValue();
		} else if (cellType == CellType.NUMERIC) {
			if (DateUtil.isCellDateFormatted(celula)) {
				cellValue = celula.getDateCellValue();
			} else {
				celula.setCellType(CellType.STRING);
				cellValue = celula.getStringCellValue();
			}
		} else if (cellType == CellType.BOOLEAN) {
			cellValue = celula.getBooleanCellValue();
		} else if (cellType == CellType.FORMULA) {
			cellValue = celula.getCellFormula();
		} else if (cellType == CellType.BLANK) {
			cellValue = "";
		}

		return cellValue;
	}

	@Async
	public void publishFile(String nomeArquivoUpload, String tipoUpload, long idUnidadeEscolhida) throws Exception {

		try {
			this.tipoUpload = tipoUpload;
			inicializarMapsDeCampos();
						
			File localFile = new File(nomeArquivoUpload);

			getSheetFromFile(new FileInputStream(localFile), 0).forEach(row -> {
				if(tipoUpload.equals(TipoUploadEnum.CONTRATO.getDescricao()) && (row.getRowNum() == 0)) {
					getFileHeader(row);
				} else 
				if(tipoUpload.equals(TipoUploadEnum.DEMANDAS.getDescricao()) && (row.getRowNum() == 1)) {
					getFileHeader(row);
				} else
				if (row.getRowNum() > 1) {	
					if(tipoUpload.equals(TipoUploadEnum.CONTRATO.getDescricao())) {
						contratoService.saveContrato((ContratoDto) getRecordsFromFile(row));	
					} else
					if(tipoUpload.equals(TipoUploadEnum.DEMANDAS.getDescricao())) {						
						demandaService.saveDemanda((DemandaDto) getRecordsFromFile(row), idUnidadeEscolhida);							
					}	
				}
			});

			localFile.delete();
			
			try {				
				UploadLog log = new UploadLog(tipoUpload, LocalDateTime.now());
				uploadRepository.save(log);
			} catch (Exception e) {
				throw new Exception("Erro ao gravar o log." + e.getMessage());
			}
			
			/**
			 * TODO PONTO DE ATENÇAO
			 */

		} catch (IOException error) {
			throw new IOException("Erro ao ler o arquivo." + error.getMessage());
		} catch (Exception error) {
			throw new Exception("Erro ao gravar no banco." + error.getMessage());
		} finally {
			limparMapsDeCampos();			
		}

		/**
		 * TODO implement a return promise
		 */
		System.out.println("Atualização finalizada com sucesso!");
	}
	
	public List<UploadLogDto> getListaUploadLog() {		
		List<UploadLogDto> listaUploadLog = new ArrayList<UploadLogDto>();
		List<UploadLogProjecaoDto> listaUploadDto = uploadRepository.getUltimaAtualizacoes(); 
		
		listaUploadDto.forEach(uploadProjecaoDto -> {
						
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime agora = LocalDateTime.parse(uploadProjecaoDto.getData().substring(0, 19), formatter);
		
			String tipo = null;
			if(uploadProjecaoDto.getTipo().equals("contrato")) {
				tipo = "Operações";
			} else
			if(uploadProjecaoDto.getTipo().equals("demanda")) {
				tipo = "Demandas";
			} else
			if(uploadProjecaoDto.getTipo().equals("saldo")) {
				tipo = "Saldo em Contas";
			} else {			
				tipo = "Pendências";
			}
			
			UploadLogDto upload = new UploadLogDto(tipo, agora);
			listaUploadLog.add(upload);
		});
		
		return listaUploadLog; 
	}
}
