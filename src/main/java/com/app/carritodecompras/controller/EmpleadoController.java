package com.app.carritodecompras.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.carritodecompras.entities.Empleado;
import com.app.carritodecompras.generics.controller.GenericController;
import com.app.carritodecompras.services.EmpleadoService;

@CrossOrigin(origins = { "http://localhost:4200/", "*" })
@RestController
@RequestMapping(path = "/api/empleados")
public class EmpleadoController extends GenericController<Empleado, Integer, EmpleadoService> {

}
