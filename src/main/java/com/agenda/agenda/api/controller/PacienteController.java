package com.agenda.agenda.api.controller;

import com.agenda.agenda.api.mapper.PacienteMapper;
import com.agenda.agenda.api.request.PacienteRequest;
import com.agenda.agenda.api.response.PacienteResponse;
import com.agenda.agenda.domain.entity.Paciente;
import com.agenda.agenda.domain.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService service;

    @RequestMapping(method = RequestMethod.GET, path = "/findAll")
    public ResponseEntity<List<Paciente>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findById/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id){
        Optional<Paciente> paciente = service.findById(id);
        if (paciente.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.OK).body(paciente.get());
    }

    @RequestMapping(method = RequestMethod.POST,path = "/save")
    public ResponseEntity<PacienteResponse> salvar(@RequestBody PacienteRequest request){

        Paciente toPacienteRequest = PacienteMapper.toPacienteRequest(request);
        Paciente pacienteSalvo = service.salvar(toPacienteRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(PacienteMapper.toPacienteResponse(pacienteSalvo));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/alterar")
    public ResponseEntity<Paciente> alterar(@RequestBody Paciente paciente){
        return ResponseEntity.status(HttpStatus.OK).body(service.salvar(paciente));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
