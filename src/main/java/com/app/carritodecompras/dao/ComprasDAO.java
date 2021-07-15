package com.app.carritodecompras.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.app.carritodecompras.entities.Compras;

public interface ComprasDAO extends JpaRepository<Compras, Integer> {

	@Transactional
	@Modifying
	@Query("update Compras c set c.img = ?1, c.estado = 'DEPOSITO ENVIADO - Espera revision' where c.id = ?2")
	public int updateFoto(String foto, Integer id);

	@Transactional
	@Modifying
	@Query("update Compras c set c.estado = ?1 where c.id = ?2")
	public int updateEstado(String estado, Integer id);

	@Query("select c from Compras c where c.estado like %?1% ")
	public List<Compras> findByLike(String like);
}
