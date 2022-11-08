package com.agenda.agenda.domain.service;

import com.agenda.agenda.domain.entity.Agenda;
import com.agenda.agenda.domain.entity.Paciente;
import com.agenda.agenda.domain.repository.AgendaRepository;
import com.agenda.agenda.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {

    @Mock
    PacienteService pacienteService;
    @Mock
    AgendaRepository agendaRepository;
    @InjectMocks
    AgendaService service;
    @Captor
    ArgumentCaptor<Agenda> agendaArgumentCaptor;

    @Test
    @DisplayName("Deve salvar agendamento com sucesso")
    void saveComSucesso() {

        //SETUP (Arrange)
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descricao do agendamento");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Nome Paciente");
        agenda.setPaciente(paciente);

        Mockito.when(pacienteService.findById(agenda.getPaciente().getId())).thenReturn(Optional.of(paciente));
        Mockito.when(agendaRepository.findByHorario(now)).thenReturn(Optional.empty());

        //TESTE (Give)
        service.save(agenda);

        //VALIDAÇÕES (Assertions)
        Mockito.verify(pacienteService).findById(agenda.getPaciente().getId());
        Mockito.verify(agendaRepository).findByHorario(now);
        Mockito.verify(agendaRepository).save(agendaArgumentCaptor.capture());
        Agenda agendaSalva = agendaArgumentCaptor.getValue();

        assertThat(agendaSalva.getPaciente()).isNotNull();
        assertThat(agendaSalva.getDataCriacao()).isNotNull();

    }

    @Test
    @DisplayName("Não deve salvar agendamento sem informar o paciente")
    void salvarErroPacienteNaoInformado(){

        //Arrange
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descricao do agendamento");
        agenda.setHorario(now);

        //Give
        BusinessException businessException = assertThrows(BusinessException.class, () -> service.save(agenda));

        //Assertions
        assertThat(businessException.getMessage()).isEqualTo("Paciente não informado");
    }

    @Test
    @DisplayName("Não deve salvar agendamento se não encontrar o paciente informado")
    void salvarErroPacienteNaoEncontrado(){

        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descricao do agendamento");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Nome Paciente");
        agenda.setPaciente(paciente);

        //Give
        Mockito.when(pacienteService.findById(agenda.getPaciente().getId())).thenReturn(Optional.empty());
        BusinessException businessException = assertThrows(BusinessException.class, () -> service.save(agenda));

        //Assertions
        assertThat(businessException.getMessage()).isEqualTo("Paciente não encontrado");
    }

    @Test
    @DisplayName("Deve retornar uma lista de Agendas")
    void retornarUmaListaDeAgenda(){
        List<Agenda> listaAgenda = new ArrayList<>();
        listaAgenda.add(new Agenda());
        listaAgenda.add(new Agenda());

        Mockito.when(agendaRepository.findAll()).thenReturn(listaAgenda);
        assertThat(service.findAll()).isEqualTo(listaAgenda);

    }
}