package com.agenda.agenda.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_hora")
    private LocalDateTime horario;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @ManyToOne
    private Paciente paciente;
}
