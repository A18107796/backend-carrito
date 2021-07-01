package com.app.carritodecompras.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.carritodecompras.entities.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

}
