package com.fiap.global.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.global.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	public Optional<Usuario> findByLogin(String usuario);
	public Optional<Usuario> findById(Long id);
}
