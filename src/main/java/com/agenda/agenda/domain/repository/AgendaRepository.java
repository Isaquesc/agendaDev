package com.agenda.agenda.domain.repository;

import com.agenda.agenda.domain.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    Optional<Agenda> findByHorario(LocalDateTime localDateTime);
}
