package com.agenda.agenda.api.controller;

import com.agenda.agenda.api.mapper.PacienteMapper;
import com.agenda.agenda.api.request.PacienteRequest;
import com.agenda.agenda.exception.response.PacienteResponse;
import com.agenda.agenda.domain.entity.Paciente;
import com.agenda.agenda.domain.service.PacienteService;
import com.agenda.agenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Object> salvar(@Valid @RequestBody PacienteRequest request){

        try {
            Paciente pacienteSalvo = service.salvar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPacienteResponse(pacienteSalvo));
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/alterar/{id}")
    public ResponseEntity<Object> alterar(@PathVariable Long id , @RequestBody PacienteRequest paciente){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(service.alterar(id, paciente)));
        }catch (BusinessException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
