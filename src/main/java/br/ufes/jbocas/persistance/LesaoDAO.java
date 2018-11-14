package br.ufes.jbocas.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.jbocas.domain.Lesao;

public interface LesaoDAO extends JpaRepository<Lesao, Long> {
}