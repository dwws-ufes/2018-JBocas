package br.ufes.jbocas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.jbocas.domain.Atendimento;
import br.ufes.jbocas.persistance.AtendimentoDAO;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {
    @Autowired
    private AtendimentoDAO atendimentoRepository;
    
    @Override
    public Atendimento cadastraAtendimento(Atendimento at) {
    	return atendimentoRepository.save(at);
    }

    @Override
    public Optional<Atendimento> findById(Long id) {
    	return atendimentoRepository.findById(id);
    }
    
    @Override
    public List<Atendimento> findAll() {
    	return atendimentoRepository.findAll();
    }
}