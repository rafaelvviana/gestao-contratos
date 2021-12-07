package br.com.rvv.gestao.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ReadAndUpdateService {

	@Async
	public void readFileAndUpdateDataBase(String nomeArquivoUpload) throws IOException {
		
		FileInputStream arquivo = new FileInputStream(new File(nomeArquivoUpload));
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(arquivo);
		Sheet sheet = workbook.getSheetAt(0);
		
		Map<Integer, List<String>> data = new HashMap<>();
				
		int i = 0;		
		for (Row row : sheet) {			
			data.put(i, new ArrayList<String>());
			
			for (Cell celula: row) {
				data.get(i).add(getCellValue(celula).toString());
			}
			i++;
		}
		
		/**
		 * TODO
		 * Change for Repository works
		 */
		data.values().forEach(linha -> {
			System.out.println("*************************************************************************************");
			System.out.println(linha);
		});
				
		
        System.out.println("Finished process.");
	}

	private Object getCellValue(Cell celula) {
		
		switch (celula.getCellTypeEnum()) {				
			case STRING:
				return celula.getStringCellValue();				
	        case NUMERIC: 
	        	if (DateUtil.isCellDateFormatted(celula)) {
	        		return celula.getDateCellValue();
	        	} else {
	        		return celula.getNumericCellValue();
	        	}
	        case BOOLEAN:
	        	return celula.getBooleanCellValue();
	        case FORMULA:
	        	return celula.getCellFormula();
	        default:
	        	return new Object();
		}
	}
	
}
