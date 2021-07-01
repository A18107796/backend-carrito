package com.app.carritodecompras.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.app.carritodecompras.entities.Cliente;
import com.app.carritodecompras.entities.Producto;

public interface ProductoDAO extends JpaRepository<Producto, Integer> {

	@Query("select a from Producto a where a.nombres like %?1% ")
	public List<Producto> findCursoByTerm(String term);
	
	@Query("select a from Producto a where a.nombres like %?1% and a.isOfertDay = ?2")
	public List<Producto> findCursoByTermAndOfert(String term, boolean ofert);
	
	@Transactional
	@Modifying
	@Query("update Producto p set p.isOfertDay = ?1 where p.id = ?2")
	public int updateOferta(boolean isOfer, Integer id);
	
	@Transactional
	@Modifying
	@Query("update Producto p set p.stock = ?1 where p.id = ?2")
	public int updateStock(int stock, Integer id);
}
