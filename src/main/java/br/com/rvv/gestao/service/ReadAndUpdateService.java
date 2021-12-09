package br.com.rvv.gestao.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
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

import br.com.rvv.gestao.controller.dto.ContratoImportadoDto;
import br.com.rvv.gestao.controller.dto.RegistroImportadoDto;
import br.com.rvv.gestao.model.Contrato;
import br.com.rvv.gestao.repository.ContratoRepository;

@Service
public class ReadAndUpdateService {

	@Autowired
	private ContratoRepository contratoRepository;

	private List<ContratoImportadoDto> listaDeRegistrosLidos = new ArrayList<>();

	private HashMap<String, Integer> mapCamposContratoDtoDeParaIndex;
	private HashMap<String, String> mapCamposPlanilhaDeParaContratoDto;
	private HashMap<Integer, String> mapCamposPlanilhaDeParaIndex = new HashMap<Integer, String>();

	public ReadAndUpdateService() throws FileNotFoundException, IOException {
		mapCamposContratoDtoDeParaIndex = getCamposContratoDtoDeParaIndex();
		mapCamposPlanilhaDeParaContratoDto = getCamposPlanilhaDeParaContratoDto();
	}

	@Async
	public void publishFile(String nomeArquivoUpload) throws IOException {

		try {
			File localFile = new File(nomeArquivoUpload);
			
			getSheetFromFile(new FileInputStream(localFile), 0).forEach(row -> {				
				getFileHeader(row);
				getRecordsFromFile(row);
			});

			saveRecords();
			
			localFile.delete();
			
			/** 
			 * TODO
			 * PONTO DE ATENÇAO
			 */
			
		} catch (IOException error) {
			throw new IOException("Erro ao ler o arquivo." + error.getMessage());
		}
		
		/**
		 * TODO 
		 * implement a return promise
		 */
		System.out.println("Atualização finalizada com sucesso!");
	}

	private void getFileHeader(Row row) {
		if (row.getRowNum() == 0) {
			row.forEach(celula -> mapCamposPlanilhaDeParaIndex.put(mapCamposPlanilhaDeParaIndex.size(),
					getCellValue(celula).toString().replace(" ", "")));
		}
	}

	private void getRecordsFromFile(Row row) {
		if (row.getRowNum() > 1) {
			listaDeRegistrosLidos.add((ContratoImportadoDto) getRegistroFromRow(row, new ContratoImportadoDto()));
		}
	}

	private void saveRecords() {
		listaDeRegistrosLidos.forEach(contratoLido -> {
			Contrato contrato = new Contrato();
			contrato.setNumeroOperacao(Integer.parseInt(contratoLido.numeroOperacao));
			contrato.setNumeroSiconv(Integer.parseInt(contratoLido.numeroSiconv));
			contratoRepository.save(contrato);
		});
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
				Integer idCampoNoBanco = getIdCampoContratoDto(celula.getColumnIndex());
				if (idCampoNoBanco != null) {
					fields[idCampoNoBanco].set(registroImportado, getCellValue(celula).toString());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});

		return registroImportado;
	}

	private Integer getIdCampoContratoDto(int i) {
		try {
			return mapCamposContratoDtoDeParaIndex
					.get(mapCamposPlanilhaDeParaContratoDto.get(mapCamposPlanilhaDeParaIndex.get(i)));
		} catch (Exception e) {
			return null;
		}
	}

	private HashMap<String, Integer> getCamposContratoDtoDeParaIndex() {
		HashMap<String, Integer> listaCamposContratoDtoDeParaIndex = new HashMap<String, Integer>();
		Field[] campos = ContratoImportadoDto.class.getFields();
		for (Field field : campos) {
			listaCamposContratoDtoDeParaIndex.put(field.getName(), listaCamposContratoDtoDeParaIndex.size());
		}
		return listaCamposContratoDtoDeParaIndex;
	}

	private HashMap<String, String> getCamposPlanilhaDeParaContratoDto() throws FileNotFoundException, IOException {
		HashMap<String, String> listaCamposPlanilhaDeParaContratoDto = new HashMap<String, String>();
		Resource resource = new ClassPathResource("files/dicionario.contrato.dto");
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
				celula.setCellType(cellType.STRING);
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

}
