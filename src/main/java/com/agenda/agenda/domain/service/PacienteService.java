package com.agenda.agenda.domain.service;

import com.agenda.agenda.domain.entity.Paciente;

import com.agenda.agenda.domain.repository.PacienteRepository;
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

    public Paciente salvar(Paciente paciente)  {

        /*if (pacienteRepository.findByCpf(paciente.getCpf()).isPresent())
            throw new BusinessException ("Cpf já cadastrado!");*/

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
