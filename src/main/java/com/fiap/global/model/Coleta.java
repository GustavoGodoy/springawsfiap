package com.fiap.global.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "coleta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_usuario")
    private Usuario user;

    private String alimento;

    @Column(name = "data_coleta")
    private Date data;

    public Coleta(Usuario usuario, String alimento, Date data) {
        this.user = usuario;
        this.alimento = alimento;
        this.data = data;
    }
}