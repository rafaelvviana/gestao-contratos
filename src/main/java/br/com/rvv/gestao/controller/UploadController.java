package br.com.rvv.gestao.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.rvv.gestao.service.ReadAndUpdateService;
import br.com.rvv.gestao.service.StorageService;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	private StorageService storage;
	
	@Autowired
	private ReadAndUpdateService readAndUpdateService;
	
	@PostMapping
	public String upload(@RequestParam MultipartFile arquivo, Model model) throws IOException, IllegalArgumentException, IllegalAccessException {
		String nomeArquivoUpload = storage.salvarArquivo(arquivo);
		
		System.out.println(nomeArquivoUpload);
		String mensagemArquivoUpload;
		if(nomeArquivoUpload == null) {
			mensagemArquivoUpload = "Erro ao fazer envio do arquivo";
		} else { 
			readAndUpdateService.readFileAndUpdateDataBase(nomeArquivoUpload);
			mensagemArquivoUpload = "Arquivo enviado com sucesso. Atualização em andamento!";
		}
		
		model.addAttribute("mensagem", mensagemArquivoUpload);
		return "upload/formUploadAguarde";
	}

	@GetMapping("form")
	public String formUpload() {
		return "upload/formUpload";
	}
	
}
