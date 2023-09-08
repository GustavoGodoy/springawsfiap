package com.fiap.global.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.global.model.Coleta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColetaDTO {

    private String alimento;

    @JsonProperty("data_coleta")
    private Date data;

    private String nomeUsuario;

    private String endereco;

    private String usuario;

    public ColetaDTO(Coleta coleta) {
        this.alimento = coleta.getAlimento();
        this.data = coleta.getData();
        this.nomeUsuario = coleta.getUser().getNome();
        this.endereco = coleta.getUser().getEndereco();
        this.usuario = coleta.getUser().getLogin();
    }
}
