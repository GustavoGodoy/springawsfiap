package com.fiap.enterprise.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UsuarioDTO {

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private String login;

    @NotNull
    private String endereco;

    @NotNull
    @Size(min = 6)
    private String senha;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data_usuario = new java.sql.Date(System.currentTimeMillis());
}
