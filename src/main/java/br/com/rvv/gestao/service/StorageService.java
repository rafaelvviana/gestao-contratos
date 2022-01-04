package br.com.rvv.gestao.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StorageService  {

	private String diretorio = "c:\\temp\\arquivos-temp\\";
	
	public String saveFile(MultipartFile arquivo) throws IOException {		
		return this.save(diretorio, arquivo);		
	}

	private String save(String diretorio, MultipartFile arquivo) throws IOException {
		
		try {
			if (arquivo == null) {
				throw new IOException("Você não informou um arquivo, repita o processo!");
			}
			Path diretorioPath = Paths.get(diretorio);
			Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
			
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			
			return arquivoPath.toString();
			
		} catch (IOException e) {
			throw new IOException("Erro ao ler o arquivo informado!");
		}
		
	}


	
}