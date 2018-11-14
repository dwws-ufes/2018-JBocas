package br.ufes.jbocas.service;

import java.util.List;

import br.ufes.jbocas.domain.Paciente;
import br.ufes.jbocas.domain.infosSexo;

public interface PacienteService {
    void cadastraPaciente(Paciente pac);

    Paciente findByCpf(String cpf);
    
    List<Paciente> findAll();
    
    List<infosSexo> estatisticaSexo();
    
    void deleteByCpf(String cpf);
}