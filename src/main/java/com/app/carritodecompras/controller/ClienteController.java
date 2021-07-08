package com.app.carritodecompras.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.carritodecompras.entities.Cliente;
import com.app.carritodecompras.generics.controller.GenericController;
import com.app.carritodecompras.services.ClienteService;

@CrossOrigin(origins = { "http://localhost:4200/", "*" })
@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController extends GenericController<Cliente, Integer, ClienteService> {

	
	public ClienteController() {
	 this.type = Cliente.class;
	}
	
}
