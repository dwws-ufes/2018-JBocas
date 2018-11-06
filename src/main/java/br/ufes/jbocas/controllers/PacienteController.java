package br.ufes.jbocas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufes.jbocas.domain.Paciente;
import br.ufes.jbocas.persistance.PacienteDAO;

@Controller
public class PacienteController {
	
	@Autowired
	private PacienteDAO pr;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index (Model model) {
		model.addAttribute("title", "NDB - Inicio");
		return "index";
	}

	@RequestMapping(value="/cadastrarPaciente", method=RequestMethod.GET)
	public String form() {
		return "paciente/formPaciente";
	}
	
	@RequestMapping(value="/cadastrarPaciente", method=RequestMethod.POST)
	public String form(Paciente paciente) {
		pr.save(paciente);
		return "redirect:/cadastrarPaciente";
	}
	
	@RequestMapping("/visualizarPacientes")
	public String listaPacientes(Model model) {
		model.addAttribute("pacientes", pr.findAll());
		model.addAttribute("title", "NDB - Visualizar Pacientes");
		return "paciente/visualizarPacientes";
	}
	
	@RequestMapping(value="/editarPacientes", method=RequestMethod.GET)
	public String listaEditaveis(Model model) {
		model.addAttribute("pacientes", pr.findAll());
		return "paciente/editarPacientes";
	}
}
