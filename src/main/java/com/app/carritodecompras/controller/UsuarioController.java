package com.app.carritodecompras.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.carritodecompras.entities.Usuario;
import com.app.carritodecompras.generics.controller.GenericController;
import com.app.carritodecompras.generics.service.GenericServiceImpl;
import com.app.carritodecompras.services.UsuarioService;


@CrossOrigin(origins = { "http://localhost:4200/", "*" })
@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController extends GenericController<Usuario, Integer, UsuarioService> {
	
	public UsuarioController() {
		this.type = Usuario.class;
	}
	
	
}
