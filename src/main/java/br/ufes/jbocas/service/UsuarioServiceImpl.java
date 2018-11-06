package br.ufes.jbocas.service;

import br.ufes.jbocas.domain.Usuario;
import br.ufes.jbocas.persistance.RoleDAO;
import br.ufes.jbocas.persistance.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioDAO userRepository;
    @Autowired
    private RoleDAO roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Usuario user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}