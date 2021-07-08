package com.app.carritodecompras.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.app.carritodecompras.entities.Compras;
import com.app.carritodecompras.generics.service.GenericService;

public interface ComprasService extends GenericService<Compras, Integer>{
	
	public Compras guardarCompra(Compras compra);
	
	public Compras cambiarEstadoCompra(Integer idCompra, String estado);

	Double getGananciasTotales();
	
	Double getGanancias(String fecha_inicio, String fecha_fin);
}
