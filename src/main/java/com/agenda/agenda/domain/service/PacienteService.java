package com.agenda.agenda.domain.service;

import com.agenda.agenda.api.mapper.PacienteMapper;
import com.agenda.agenda.api.request.PacienteRequest;
import com.agenda.agenda.domain.entity.Paciente;

import com.agenda.agenda.domain.repository.PacienteRepository;
import com.agenda.agenda.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteMapper mapper;

    public Paciente salvar(PacienteRequest pacienteRequest)  {

        Optional<Paciente> pacienteCPF = pacienteRepository.findByCpf(pacienteRequest.getCpf());

        if (pacienteCPF.isPresent()){
            throw new BusinessException("CPF Já Cadastrado");
        }

        return pacienteRepository.save(mapper.toPacienteRequest(pacienteRequest));
    }

    public Paciente alterar(Long id, PacienteRequest pacienteRequest){
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if(optionalPaciente.isEmpty())
            throw new BusinessException("Paciente não encontrado");

        Paciente paciente = mapper.toPacienteRequest(pacienteRequest);
        paciente.setId(id);

        return pacienteRepository.save(paciente);

    }

    public Optional<Paciente> findById(Long id){
        return pacienteRepository.findById(id);
    }

    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }

    public void delete(Long id){
        pacienteRepository.deleteById(id);
    }
}
