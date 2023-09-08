package com.fiap.global.response;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.global.model.Coleta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDTO {

    private String alimento;

    @JsonProperty("data_coleta")
    private String data;

    private String nomeUsuario;

    private String endereco;

    private String usuario;

    public ResponseDTO(Coleta coleta) {
        this.alimento = coleta.getAlimento();
        this.data = coleta.getData().toString().substring(0, 10);
        this.nomeUsuario = coleta.getUser().getNome();
        this.endereco = coleta.getUser().getEndereco();
        this.usuario = coleta.getUser().getLogin();
    }
}
