package com.app.carritodecompras.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.carritodecompras.entities.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.usuario =?1")
	public Usuario findByUsername(String email);
}
