package br.ufes.jbocas.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.jbocas.domain.Atendimento;

public interface AtendimentoDAO extends JpaRepository<Atendimento, Long> {
}