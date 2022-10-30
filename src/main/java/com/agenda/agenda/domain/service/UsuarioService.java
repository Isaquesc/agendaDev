package com.agenda.agenda.domain.service;

import com.agenda.agenda.domain.entity.Usuario;
import com.agenda.agenda.domain.repository.UsuarioRepository;
import com.agenda.agenda.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id){
        Usuario usuario = repository.findById(id).orElseThrow(() -> new BusinessException("Usuario não encontrado"));
        return usuario;
    }

    public Usuario save(Usuario usuario){
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return repository.save(usuario);
    }

}
