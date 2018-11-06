package br.ufes.jbocas.persistance;

import br.ufes.jbocas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}