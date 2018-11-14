package br.ufes.jbocas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufes.jbocas.service.PacienteService;

@Controller
public class PacienteController {
	
	@Autowired
	private PacienteService pacienteService;
	
	@RequestMapping(value="/paciente/visualizarPacientes", method=RequestMethod.GET)
	public String listaPacientes(Model model) {
		model.addAttribute("pacientes", pacienteService.findAll());
		model.addAttribute("title", "NDB - Visualizar Pacientes");
		return "paciente/visualizarPacientes";
	}
	
	@RequestMapping(value="/paciente/visualizarPaciente", params = "paciente",method=RequestMethod.GET)
	public String visualizarPaciente(@RequestParam("paciente") String cpf, Model model) {
		model.addAttribute("paciente", pacienteService.findByCpf(cpf));
		model.addAttribute("title", "NDB - Visualizar Paciente");
		return "paciente/visualizarPaciente";
	}
	
	@RequestMapping(value="/paciente/deletar", params = "paciente",method=RequestMethod.GET)
	public String deletarPaciente(@RequestParam("paciente") String cpf, Model model) {
		pacienteService.deleteByCpf(cpf);
		return "redirect:/paciente/visualizarPacientes";
	}
}
