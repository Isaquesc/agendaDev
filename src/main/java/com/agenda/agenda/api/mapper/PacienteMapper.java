package com.agenda.agenda.api.mapper;

import com.agenda.agenda.api.request.PacienteRequest;
import com.agenda.agenda.api.response.PacienteResponse;
import com.agenda.agenda.domain.entity.Paciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPacienteRequest (PacienteRequest request){
        return mapper.map(request, Paciente.class);
    }

    public PacienteResponse toPacienteResponse (Paciente response){
        return mapper.map(response, PacienteResponse.class);
    }

    public  List<PacienteResponse> toPacienteResponseList (List<Paciente> listPaciente){
        return listPaciente.stream()
                .map(this::toPacienteResponse)
                .collect(Collectors.toList());
    }

}
