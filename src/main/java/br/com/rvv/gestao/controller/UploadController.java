package br.com.rvv.gestao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.rvv.gestao.model.enums.PaginasEnum;
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
	public String upload(@RequestParam MultipartFile arquivo, @RequestParam("tipoUpload") String tipoUpload,
			@RequestParam(name="idUnidadeEscolhida", required = false) Long idUnidadeEscolhida,
			Model model) {
		
		String mensagemArquivoUpload;
		
		if(!tipoUpload.equals("contrato") && (!tipoUpload.equals("demanda"))) {
			return PaginasEnum.NAO_ENCONTRADO.getDescricao();
		}
		
		if(idUnidadeEscolhida == null) {
			idUnidadeEscolhida = Long.valueOf(0);
		}
		
		try {
			readAndUpdateService.publishFile(storage.saveFile(arquivo), tipoUpload, idUnidadeEscolhida);
			mensagemArquivoUpload = "Arquivo enviado com sucesso. Atualização em andamento!";
		} catch (Exception e) {
			return PaginasEnum.ERRO.getDescricao();
		}
		
		model.addAttribute("mensagem", mensagemArquivoUpload);
		return PaginasEnum.UPLOAD_AGUARDE.getDescricao(); 
	}

	@GetMapping("/{tipo}")
	public String formUploadOperacoes(@PathVariable("tipo") String tipoUpload, Model model) {		
		
		String sTipoUpload = null;
		
		if(tipoUpload.equals("contrato")) {
			sTipoUpload = "Operações";
		}			
		if(tipoUpload.equals("demanda")) {
			sTipoUpload = "Demandas";
		}			
		if(tipoUpload.equals("saldo")) {
			sTipoUpload = "Saldo de Contas";
		}
		if(tipoUpload.equals("pendencia")) {			
			sTipoUpload = "Pendências";
		}		
		if(sTipoUpload == null) {
			return "erro-inesperado.html";
		}
		
		model.addAttribute("tipoUpload", tipoUpload);
		model.addAttribute("sTipoUpload", sTipoUpload);
		return PaginasEnum.UPLOAD_FORM.getDescricao();
	}	
}
