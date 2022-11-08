package com.agenda.agenda.api.controller;


import com.agenda.agenda.domain.entity.Usuario;
import com.agenda.agenda.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @RequestMapping(method = RequestMethod.GET, path = ("/findAll"))
    public List<Usuario> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = ("/{id}"))
    public Optional<Usuario> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = ("/save"))
        public Usuario save (@RequestBody Usuario usuario){
            return service.save(usuario);
        }

}
