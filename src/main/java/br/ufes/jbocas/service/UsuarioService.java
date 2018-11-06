package br.ufes.jbocas.service;

import br.ufes.jbocas.domain.Usuario;

public interface UsuarioService {
    void save(Usuario user);

    Usuario findByUsername(String username);
}