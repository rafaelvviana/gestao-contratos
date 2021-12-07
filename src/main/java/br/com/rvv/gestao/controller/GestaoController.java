package br.com.rvv.gestao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GestaoController {

	@RequestMapping("/")	
	public String home(Model model) {		
		return "home"; 
	}
	
}
