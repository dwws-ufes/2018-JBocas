package br.ufes.jbocas.controllers;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufes.jbocas.domain.Paciente;
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
		Paciente paciente = pacienteService.findByCpf(cpf);
		model.addAttribute("paciente", paciente);
		model.addAttribute("comment", buscarDescricao(paciente.getCidade()));
		model.addAttribute("title", "NDB - Visualizar Paciente");
		return "paciente/visualizarPaciente";
	}
	
	@RequestMapping(value="/paciente/deletar", params = "paciente",method=RequestMethod.GET)
	public String deletarPaciente(@RequestParam("paciente") String cpf, Model model) {
		pacienteService.deleteByCpf(cpf);
		return "redirect:/paciente/visualizarPacientes";
	}
	
	public String buscarDescricao(String name) {
			if (name != null && name.length() > 3) {
				String query2 = 
								"PREFIX type: <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>" +
								"PREFIX comment: <http://www.w3.org/2000/01/rdf-schema#comment>" +
								"PREFIX label: <http://www.w3.org/2000/01/rdf-schema#label>" +
								"PREFIX yago: <http://dbpedia.org/class/yago/>" + 
								"PREFIX lat: <http://www.w3.org/2003/01/geo/wgs84_pos#lat>" +
								"PREFIX long: <http://www.w3.org/2003/01/geo/wgs84_pos#long>" +
								"SELECT ?comment " +
								"WHERE {" +
								   "?city type: yago:WikicatPopulatedPlacesInEspíritoSanto." +
								   "?city label: ?citylabel." +
								   "?city comment: ?comment ." +
								   "FILTER (regex(lcase(str(?citylabel)), \"" + name.toLowerCase() + "\")) " +
								   "FILTER (lang(?citylabel) = 'pt')" +
								   "FILTER (lang(?comment) = 'pt')" +
								"} ORDER BY DESC(?citylabel) LIMIT 1";
				QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query2);
				ResultSet results = queryExecution.execSelect();
				if (results.hasNext()) {
					QuerySolution querySolution = results.next();
					System.out.println("Entrou");
					System.out.println(querySolution.getLiteral("comment").getLexicalForm());
					return querySolution.getLiteral("comment").getLexicalForm();
				}
				else
				{
					return name + " ainda não pussui uma descrição.";
				}
		}
			return name + " ainda não pussui uma descrição.";
	}
}
