package com.agenda.agenda.api.mapper;


import com.agenda.agenda.api.request.AgendaRequest;
import com.agenda.agenda.api.response.AgendaResponse;
import com.agenda.agenda.domain.entity.Agenda;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    private final ModelMapper mapper;

    public Agenda toAgenda(AgendaRequest agendaRequest){
        return mapper.map(agendaRequest, Agenda.class);
    }

    public AgendaResponse toAgendaResponse(Agenda agenda) {
        return mapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> toAgendaResponseList(List<Agenda> agendaList){
        return agendaList.stream().map(this::toAgendaResponse)
                .collect(Collectors.toList());
    }

}
