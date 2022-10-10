package com.agenda.agenda.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

    private String nome;
    private String sobreNome;
    private String email;
    private String cpf;

}
