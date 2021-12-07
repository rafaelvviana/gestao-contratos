package br.com.rvv.gestao.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StorageService  {

	private String diretorio = "c:\\temp\\arquivos-caixa\\";
	
	public String salvarArquivo(MultipartFile arquivo) {
		return this.salvar(diretorio, arquivo);		
	}

	private String salvar(String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			
			return arquivoPath.toString();
		} catch (IOException e) {
			return "null";
		}
		
	}


	
}