package br.ufes.jbocas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufes.jbocas.domain.infosSexo;
import br.ufes.jbocas.service.PacienteService;
@Controller
public class EstatisticaController {
	
	@Autowired
	private PacienteService pacienteService;

	@RequestMapping(value="/estatistica/graficos", method=RequestMethod.GET)
	public String index(Model model) {
		List<infosSexo> infoS = pacienteService.estatisticaSexo();
		model.addAttribute("title", "NDB - Graficos");
		model.addAttribute("infosSexo", infoS);
		return "estatistica/graficos";
	}
}
