package com.app.carritodecompras.services;

import java.util.List;

import com.app.carritodecompras.entities.Producto;
import com.app.carritodecompras.generics.service.GenericService;

public interface ProductoService extends GenericService<Producto, Integer> {
	
	public List<Producto> findCursoByTerm(String term, boolean ofert);
	
	public void setOfert(boolean isOfert, Integer id);
	
	public boolean CheckStock(Integer idProducto,  Integer FrontStock);
	
	public boolean reduceStock(Integer idProducto, Integer frontStock);
}
