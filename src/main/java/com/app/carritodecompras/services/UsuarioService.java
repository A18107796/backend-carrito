package com.app.carritodecompras.services;

import com.app.carritodecompras.dao.UsuarioDAO;

import com.app.carritodecompras.generics.service.GenericService;import com.app.carritodecompras.entities.Usuario;

public interface UsuarioService extends GenericService<Usuario, Integer> {

	public Usuario findByUsername(String username);
}
