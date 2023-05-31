package com.fiap.global.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fiap.global.request.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
public class Usuario {

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nome_usuario")
	@NotNull
	private String nome;

	@Column(name = "email_usuario")
	@NotNull
	private String email;

	@Column(name = "usuario")
	@NotNull
	private String login;

	@Column(name = "foto_usuario")
	@NotNull
	private String endereco;

	@Column(name = "senha_usuario")
	@NotNull
	@Size(min = 6)
	private String senha;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data_usuario = new java.sql.Date(System.currentTimeMillis());

}