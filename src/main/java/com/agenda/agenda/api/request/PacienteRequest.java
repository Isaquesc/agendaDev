package com.agenda.agenda.api.request;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

    @NotNull(message = "Campo NOME obrigatorio")
    private String nome;

    @NotNull(message = "Campo SOBRENOME obrigatorio")
    private String sobreNome;

    @NotNull(message = "Campo EMAIL obrigatorio")
    @Email
    private String email;

    @NotNull(message = "Campo CPF obrigatorio")
    @CPF
    private String cpf;

}
