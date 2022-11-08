package com.agenda.agenda.domain.service;

import com.agenda.agenda.domain.entity.Agenda;
import com.agenda.agenda.domain.entity.Paciente;
import com.agenda.agenda.domain.repository.AgendaRepository;
import com.agenda.agenda.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgendaService {

    @Autowired
    private AgendaRepository repository;
    @Autowired
    private PacienteService pacienteService;


    public List<Agenda> findAll() {
        return repository.findAll();
    }

    public Optional<Agenda> findById(Long id) {
        return repository.findById(id);
    }

    public Agenda save(Agenda agenda) {

        if (agenda.getPaciente() == null) {
            throw new BusinessException("Paciente não informado");
        }

        Optional<Paciente> optionalPaciente = pacienteService.findById(agenda.getPaciente().getId());
        if (optionalPaciente.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }

        Optional<Agenda> optionalHorario = repository.findByHorario(agenda.getHorario());

        if (optionalHorario.isPresent()) {
            throw new BusinessException("Já existe agendamento para este horário");
        }

        agenda.setPaciente(optionalPaciente.get());
        agenda.setDataCriacao(LocalDate.now());
        return repository.save(agenda);
    }
}
