package br.ufes.jbocas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.ufes.jbocas.domain.Atendimento;
import br.ufes.jbocas.domain.Lesao;
import br.ufes.jbocas.domain.Paciente;
import br.ufes.jbocas.persistance.LesaoDAO;
import br.ufes.jbocas.service.AtendimentoService;
import br.ufes.jbocas.service.PacienteService;
@Controller
@SessionAttributes({"atendimento", "pacienteForm" })
public class AtendimentoController {
	
	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private LesaoDAO lesaoRepository;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("pacientes", pacienteService.findAll());
		model.addAttribute("title", "NDB - Inicio");
		return "index";
	}
	
	@RequestMapping(value="/atendimento/inicioAtendimento", method=RequestMethod.GET)
	public String inicioAtendimento(Model model) {
		model.addAttribute("pacienteForm", new Paciente());
		model.addAttribute("title", "NDB - Inicio Atendimento");
		return "atendimento/inicioAtendimento";
	}

	@RequestMapping(value="/atendimento/inicioAtendimento", method=RequestMethod.POST)
	public String buscarCadastrarNovoPaciente(@ModelAttribute("pacienteForm") Paciente pacienteForm, Model model) {
		Paciente pc = pacienteService.findByCpf(pacienteForm.getCpf());
		String url;
		if(pc == null) {
			url = "redirect:/atendimento/paciente";
		}
		else {
			model.addAttribute("pacienteForm", pc);
			url = "redirect:/atendimento/paciente?paciente=" + pc.getCpf();
		}
		model.addAttribute("atendimento", new Atendimento());
		return url;
	}
	
	@RequestMapping(value = "/atendimento/paciente", method = RequestMethod.GET)
	public String inicioPaciente(@ModelAttribute("pacienteForm") Paciente pacienteForm, Model model) {
		model.addAttribute("atendimento", new Atendimento());
		return "atendimento/paciente";
	}
	
	@RequestMapping(value = "/atendimento/paciente", method = RequestMethod.POST)
	public String cadastraPaciente(@ModelAttribute("pacienteForm") Paciente pacienteForm, Model model) {
		pacienteService.cadastraPaciente(pacienteForm);

		return "redirect:/atendimento/ficha?paciente=" + pacienteForm.getCpf();
	}
	
	@RequestMapping(value = "/atendimento/paciente", params = "paciente", method = RequestMethod.GET)
	public String editarPaciente(@ModelAttribute("pacienteForm") Paciente pacienteForm, @RequestParam("paciente") String cpf,  Model model) {
		Paciente pac = pacienteService.findByCpf(cpf);
		model.addAttribute("pacienteForm", pac);
		model.addAttribute("atendimento", new Atendimento());
		return "atendimento/paciente";
	}
	
	@RequestMapping(value = "/atendimento/ficha", params = "paciente", method = RequestMethod.GET)
	public String cadastrarAtendimento(@ModelAttribute("atendimento") Atendimento at, @RequestParam("paciente") String cpf, Model model) {
		model.addAttribute("paciente", pacienteService.findByCpf(cpf));
		model.addAttribute("lesao", new Lesao());
		model.addAttribute("lesoes", at.getLesoes());
		return "atendimento/ficha";
	}
	
	@RequestMapping(value = "/atendimento/ficha", params = { "paciente", "lesaoId" }, method = RequestMethod.GET)
	public String deletaLesao(@ModelAttribute("atendimento") Atendimento at, @RequestParam("paciente") String cpf, @RequestParam("lesaoId") Long lesaoId, Model model) {
		model.addAttribute("paciente", pacienteService.findByCpf(cpf));
		at.getLesoes().remove(lesaoRepository.getOne(lesaoId));
		lesaoRepository.deleteById(lesaoId);
		model.addAttribute("lesao", new Lesao());
		model.addAttribute("lesoes", at.getLesoes());
		model.addAttribute("atendimento", at);
		
		return "atendimento/ficha";
	}
	
	@RequestMapping(value = "/atendimento/ficha", params = { "paciente", "atendimentoId" }, method = RequestMethod.GET)
	public String editarAtendimento(@RequestParam("atendimentoId") Long id, @RequestParam("paciente") String cpf, Model model) {
		Atendimento at = atendimentoService.findById(id).get();
		model.addAttribute("atendimento", at);
		model.addAttribute("paciente", pacienteService.findByCpf(cpf));
		model.addAttribute("lesao", new Lesao());
		model.addAttribute("lesoes", at.getLesoes());
		return "atendimento/ficha";
	}
	
	@RequestMapping(value = "/atendimento/ficha", params = { "paciente", "atendimentoId", "incluirLesao" }, method = RequestMethod.POST)
	public String addRowEditando(@ModelAttribute("atendimento") Atendimento at,
								@RequestParam("paciente") String cpf,
								@RequestParam("atendimentoId") Long id,
								@ModelAttribute("lesao") Lesao lesao,
								Model model) {
		at = atendimentoService.findById(id).get();
		lesaoRepository.save(lesao);
		at.getLesoes().add(lesao);
		model.addAttribute("atendimento", at);
		return "redirect:/atendimento/ficha?paciente=" + cpf + "&atendimentoId=" + at.getId();
	}
	
	@RequestMapping(value = "/atendimento/ficha", params = { "paciente", "incluirLesao"}, method = RequestMethod.POST)
	public String addRow(@ModelAttribute("atendimento") Atendimento at,
						@RequestParam("paciente") String cpf,
						@ModelAttribute("lesao") Lesao lesao,
						Model model) {
		at.getLesoes().add(lesao);
		model.addAttribute("atendimento", at);
		model.addAttribute("lesoes", at.getLesoes());
		return "redirect:/atendimento/ficha?paciente=" + cpf + "#lesoesFrag";
	}
	
	@RequestMapping(value = "/atendimento/ficha", params = { "paciente" }, method = RequestMethod.POST)
	public String finalizarAtendimento(@ModelAttribute("atendimento") Atendimento at, @RequestParam("paciente") String cpf, Model model) {
		Paciente pac = pacienteService.findByCpf(cpf);
		pac.getAtendimentos().add(at);
		pacienteService.cadastraPaciente(pac);
		model.addAttribute("atendimento", at);
		return "redirect:/atendimento/resumo?paciente=" + cpf;
	}
	
	@RequestMapping(value = "/atendimento/resumo", params = { "paciente" }, method = RequestMethod.GET)
	public String finalizarFicha(@ModelAttribute("atendimento") Atendimento at, @RequestParam("paciente") String pacienteCpf, Model model) {
		model.addAttribute("atendimento", at);
		model.addAttribute("paciente", pacienteService.findByCpf(pacienteCpf));
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
		model.addAttribute("pacientes", pacienteService.findAll());
		return "paciente/editarPacientes";
	}
	
	@RequestMapping(value="/atendimento/visualizarAtendimentos", method=RequestMethod.GET)
	public String listaAtendimentos(Model model) {
		model.addAttribute("atendimentos", atendimentoService.findAll());
		model.addAttribute("title", "NDB - Visualizar Atendimentos");
		return "atendimento/visualizarAtendimentos";
	}
}
