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
    private final PacienteMapper mapper;

    @RequestMapping(method = RequestMethod.GET, path = "/findAll")
    public ResponseEntity<List<PacienteResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponseList(service.findAll()));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findById/{id}")
    public ResponseEntity<PacienteResponse> findById(@PathVariable Long id){
        Optional<Paciente> paciente = service.findById(id);
        if (paciente.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(paciente.get()));
    }

    @RequestMapping(method = RequestMethod.POST,path = "/save")
    public ResponseEntity<PacienteResponse> salvar(@RequestBody PacienteRequest request){

        Paciente toPacienteRequest = mapper.toPacienteRequest(request);
        Paciente pacienteSalvo = service.salvar(toPacienteRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPacienteResponse(pacienteSalvo));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/alterar")
    public ResponseEntity<PacienteResponse> alterar(@RequestBody Paciente paciente){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(service.salvar(paciente)));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
