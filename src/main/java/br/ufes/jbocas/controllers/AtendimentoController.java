package br.ufes.jbocas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufes.jbocas.domain.Atendimento;
import br.ufes.jbocas.domain.Paciente;
import br.ufes.jbocas.persistance.PacienteDAO;

@Controller
public class AtendimentoController {
	
	@Autowired
	private PacienteDAO pr;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index (Model model) {
		model.addAttribute("title", "NDB - Inicio");
		return "index";
	}

	@RequestMapping(value="atendimento/paciente", method=RequestMethod.GET)
	public String inicioAtendimento(Model model) {
		model.addAttribute("title", "NDB - Atendimento");
		return "atendimento/paciente";
	}
	
	@RequestMapping(value = "/atendimento/paciente", method = RequestMethod.POST)
	public String cadastraPaciente(@ModelAttribute("pacienteForm") Paciente pacienteForm, Model model) {
		pr.save(pacienteForm);

		model.addAttribute("paciente", pacienteForm);

		return "redirect:/atendimento/ficha";
	}
	
	@RequestMapping(value = "/atendimento/paciente", params = "editar", method = RequestMethod.GET)
	public String editarPaciente(@ModelAttribute("pacienteForm") Paciente pacienteForm, Model model) {
		model.addAttribute("paciente", pacienteForm);
		return "atendimento/paciente";
	}
	
	@RequestMapping(value = "/atendimento/ficha", method = RequestMethod.GET)
	public String fichaAtendimento(@ModelAttribute("pacienteForm") Paciente pacienteForm, Model model) {
		model.addAttribute("paciente", pacienteForm);
		return "atendimento/ficha";
	}
	
	@RequestMapping(value = "/atendimento/ficha", params = "editar", method = RequestMethod.GET)
	public String editarAtendimento(@ModelAttribute("atendimentoForm") Atendimento atendimentoForm, Model model) {
		model.addAttribute("atendimento", atendimentoForm);
		return "atendimento/paciente";
	}
	
	@RequestMapping(value = "/atendimento/resumo", method = RequestMethod.GET)
	public String finalizarFicha(@ModelAttribute("atendimentoForm") Atendimento atendimentoForm, Model model) {
		model.addAttribute("atendimento", atendimentoForm);
		return "atendimento/resumo";
	}
	
	@RequestMapping(value = "/atendimento/atendimentoPDF", method = RequestMethod.GET)
	public String gerarPdfAtendimento(@ModelAttribute("atendimentoForm") Atendimento atendimentoForm, Model model) {
		model.addAttribute("atendimento", atendimentoForm);
		return "atendimento/atendimentoPDF";
	}
	
	@RequestMapping(value = "/atendimento/requisicaoPDF", method = RequestMethod.GET)
	public String gerarPdfRequisicao(@ModelAttribute("atendimentoForm") Atendimento atendimentoForm, Model model) {
		model.addAttribute("atendimento", atendimentoForm);
		return "atendimento/requisicaoPDF";
	}
	
	@RequestMapping(value="/editarPacientes", method=RequestMethod.GET)
	public String listaEditaveis(Model model) {
		model.addAttribute("pacientes", pr.findAll());
		return "paciente/editarPacientes";
	}
}
