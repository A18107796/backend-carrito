package com.app.carritodecompras.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.carritodecompras.entities.Detalle_compras;

public interface DetalleComprasDAO extends JpaRepository<Detalle_compras, Integer> {

	@Query("select sum(c.subtotal) from Detalle_compras c")
	Double getGananciasTotales();

	@Query(nativeQuery = true, value = "select sum(a.subtotal) from compras c join detalle_compras a on a.id_compras = c.id where c.fecha_compras BETWEEN ?1 and ?2")
	Double getGanancias(String fecha_inicio, String fecha_fin);
}
