package br.ufes.jbocas.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.jbocas.domain.Paciente;
import br.ufes.jbocas.domain.infosSexo;
import br.ufes.jbocas.persistance.PacienteDAO;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteDAO pacienteRepository;

    @Override
    public void cadastraPaciente(Paciente paciente) {
    	Paciente pc = pacienteRepository.findByCpf(paciente.getCpf());
    	if(pc == null) {
    		pacienteRepository.save(paciente);
    	}
    	else {
    		paciente.setId(pc.getId());
    		pacienteRepository.save(paciente);
    	}
    }

    @Override
    public Paciente findByCpf(String cpf) {
    	Paciente p = pacienteRepository.findByCpf(cpf);
    	if(p != null)
			p.setIdade(Period.between(p.getNascimento(), LocalDate.now()).getYears());
        return p;
    }
    
    @Override
    public List<Paciente> findAll() {
    	List<Paciente> someIterable = pacienteRepository.findAll();
		for (Iterator<Paciente> i = someIterable.iterator(); i.hasNext();) {
			Paciente p = i.next();
    	    p.setIdade(Period.between(p.getNascimento(), LocalDate.now()).getYears());
    	}
    	return pacienteRepository.findAll();
    }

	@Override
	public List<infosSexo> estatisticaSexo() {
		List<infosSexo> ls = new ArrayList<infosSexo>();
		infosSexo m = new infosSexo();
		m.setLabel("M");
		m.setContagem(pacienteRepository.countBySexo("M"));
		infosSexo f = new infosSexo();
		f.setLabel("F");
		f.setContagem(pacienteRepository.countBySexo("F"));
		ls.add(m);
		ls.add(f);
		return ls;
	}
	
	@Override
	@Transactional
	public void deleteByCpf(String cpf) {
		pacienteRepository.deleteByCpf(cpf);
	}
}