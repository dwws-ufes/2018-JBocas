package br.ufes.jbocas.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufes.jbocas.domain.Role;


public interface RoleDAO extends JpaRepository<Role, Long>{

}