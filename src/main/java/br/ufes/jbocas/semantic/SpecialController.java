package br.ufes.jbocas.semantic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufes.jbocas.domain.Paciente;
import br.ufes.jbocas.service.PacienteService;

@Controller
public class SpecialController {
	
	@Autowired
	private PacienteService pacienteService;
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public @ResponseBody void generateReport( 
	        HttpServletRequest req, 
	        HttpServletResponse resp) throws IOException {
		resp.setContentType("text/xml");
		
		// Lista de todas as localidades cadastradas
		List<Paciente> pacientes = pacienteService.findAll();
		
		Model model = ModelFactory.createDefaultModel();
		
		// Yago
		String yagoNS = "http://dbpedia.org/class/yago/";
		model.setNsPrefix("yago", yagoNS);
		Resource yagoPopulatedPlacesInEspíritoSanto = ResourceFactory.createResource(yagoNS + "WikicatPopulatedPlacesInEspíritoSanto");

		String dboNS = "http://dbpedia.org/ontology/";
		model.setNsPrefix("dbo", dboNS);
		Property pessoa = ResourceFactory.createProperty(dboNS + "Person");
		Property nome = ResourceFactory.createProperty(dboNS + "birthName");
		Property mae = ResourceFactory.createProperty(dboNS + "mother");
		Property cidade = ResourceFactory.createProperty(dboNS + "livingPlace");
		
		String myNS = "http://localhost:8080/report/";
		
		for (Paciente paciente : pacientes) 
		{
			model.createResource(myNS + paciente.getId())
			.addProperty(RDF.type, pessoa)
			.addProperty(RDFS.label, paciente.getNome())
			.addProperty(nome, paciente.getNome())
			.addProperty(mae, paciente.getNomeMae())
			.addProperty(cidade, model.createResource().addProperty(RDF.type, yagoPopulatedPlacesInEspíritoSanto).addProperty(RDFS.label, paciente.getCidade()));
		}
		
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
		}
	}
}
