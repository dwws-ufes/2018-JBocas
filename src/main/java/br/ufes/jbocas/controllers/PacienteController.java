package br.ufes.jbocas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufes.jbocas.persistance.PacienteDAO;

@Controller
public class PacienteController {
	
	@Autowired
	private PacienteDAO pr;
	
	@RequestMapping(value="/visualizarPacientes", method=RequestMethod.GET)
	public String listaPacientes(Model model) {
		model.addAttribute("pacientes", pr.findAll());
		model.addAttribute("title", "NDB - Visualizar Pacientes");
		return "paciente/visualizarPacientes";
	}
}
