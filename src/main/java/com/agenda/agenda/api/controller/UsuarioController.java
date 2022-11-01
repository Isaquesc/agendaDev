package com.agenda.agenda.api.controller;


import com.agenda.agenda.domain.entity.Usuario;
import com.agenda.agenda.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @RequestMapping(method = RequestMethod.POST, path = "/save")
    public String save(@Valid @RequestBody Usuario usuario){
        service.save(usuario);
        return "Usuario cadastrado com sucesso";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findAll")
    public List<Usuario> findAll(){
        return service.findAll();
    }
}
