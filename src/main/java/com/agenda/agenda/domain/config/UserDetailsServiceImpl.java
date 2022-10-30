package com.agenda.agenda.domain.config;

import com.agenda.agenda.domain.entity.Usuario;
import com.agenda.agenda.domain.repository.UsuarioRepository;
import com.agenda.agenda.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username).orElseThrow(() -> new BusinessException("Usuario não encontrado" + username));
        return usuario;
    }
}
