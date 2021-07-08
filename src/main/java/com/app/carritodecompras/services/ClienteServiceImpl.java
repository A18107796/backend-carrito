package com.app.carritodecompras.services;

import org.springframework.stereotype.Service;

import com.app.carritodecompras.dao.ClienteDAO;
import com.app.carritodecompras.entities.Cliente;
import com.app.carritodecompras.generics.service.GenericServiceImpl;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, ClienteDAO, Integer> implements ClienteService {
	
	

}
