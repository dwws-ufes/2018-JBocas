package br.ufes.jbocas.service;

import java.util.List;
import java.util.Optional;

import br.ufes.jbocas.domain.Atendimento;

public interface AtendimentoService {
    Optional<Atendimento> findById(Long id);
    
    Atendimento cadastraAtendimento(Atendimento at);
    
    List<Atendimento> findAll();
}