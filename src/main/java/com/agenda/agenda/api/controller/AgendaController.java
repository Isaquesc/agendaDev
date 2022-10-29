package com.agenda.agenda.api.controller;

import com.agenda.agenda.api.mapper.AgendaMapper;
import com.agenda.agenda.api.request.AgendaRequest;
import com.agenda.agenda.api.response.AgendaResponse;
import com.agenda.agenda.domain.entity.Agenda;
import com.agenda.agenda.domain.service.AgendaService;
import com.agenda.agenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService service;
    private final AgendaMapper mapper;

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public ResponseEntity<List<AgendaResponse>> findAll() {
        List<AgendaResponse> agendaResponses = mapper.toAgendaResponseList(service.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(agendaResponses);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<AgendaResponse> findByid(@PathVariable Long id) {
        Optional<Agenda> optionalAgenda = service.findById(id);

        if (optionalAgenda.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toAgendaResponse(optionalAgenda.get()));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/salvar")
    public ResponseEntity<Object> save(@Valid @RequestBody AgendaRequest request) {
        try {
            Agenda agenda = service.save(mapper.toAgenda(request));
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toAgendaResponse(agenda));
        } catch (BusinessException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
