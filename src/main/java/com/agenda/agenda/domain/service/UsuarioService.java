package com.agenda.agenda.domain.service;

import com.agenda.agenda.domain.entity.Usuario;
import com.agenda.agenda.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {

        Optional<Usuario> optionalUsuario = repository.findByUsuario(usuario);

        if (optionalUsuario.isEmpty())
            throw new UsernameNotFoundException("Usuario não enconstrado");


        return new User(optionalUsuario.get().getUsuario(), optionalUsuario.get().getSenha(), new ArrayList<>());
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario save(Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    public Optional<Usuario> findById(Long id){
        return repository.findById(id);
    }

}
