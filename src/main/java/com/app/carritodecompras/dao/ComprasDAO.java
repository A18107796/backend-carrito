package com.app.carritodecompras.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.carritodecompras.entities.Compras;

public interface ComprasDAO extends JpaRepository<Compras, Integer>{

}
