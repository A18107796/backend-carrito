package com.app.carritodecompras.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.carritodecompras.entities.Compras;

public interface ComprasDAO extends JpaRepository<Compras, Integer>{


}

