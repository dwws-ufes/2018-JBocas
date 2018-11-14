package br.ufes.jbocas.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.jbocas.domain.Paciente;

public interface PacienteDAO extends JpaRepository<Paciente, Long> {
	
	boolean existsByCpf(String cpf);
	
	Paciente findByCpf(String cpf);

	Long countBySexo(String string);
	
	void deleteByCpf(String cpf);
}