package com.app.carritodecompras.services;

import org.springframework.stereotype.Service;

import com.app.carritodecompras.dao.EmpleadoDAO;
import com.app.carritodecompras.entities.Empleado;
import com.app.carritodecompras.generics.service.GenericServiceImpl;

@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado, EmpleadoDAO, Integer> implements EmpleadoService {

}
