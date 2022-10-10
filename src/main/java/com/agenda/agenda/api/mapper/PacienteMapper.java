package com.agenda.agenda.api.mapper;

import com.agenda.agenda.api.request.PacienteRequest;
import com.agenda.agenda.api.response.PacienteResponse;
import com.agenda.agenda.domain.entity.Paciente;

public class PacienteMapper {

    public static Paciente toPacienteRequest (PacienteRequest request){
        return new Paciente(null,
                request.getNome(),
                request.getSobreNome(),
                request.getEmail(),
                request.getCpf());
    }

    public static PacienteResponse toPacienteResponse (Paciente response){
        return new PacienteResponse(response.getId(),
                response.getEmail());
    }


}
