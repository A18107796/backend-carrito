package com.app.carritodecompras.services;

import java.util.List;
import java.util.Observable;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.carritodecompras.dao.ProductoDAO;
import com.app.carritodecompras.entities.Producto;
import com.app.carritodecompras.exceptions.NotFoundException;
import com.app.carritodecompras.generics.service.GenericServiceImpl;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, ProductoDAO, Integer> implements ProductoService {

	@Override
	public List<Producto> findCursoByTerm(String term, boolean ofert) {
		if (ofert) {
			return dao.findCursoByTermAndOfert(term, ofert);
		}
		return dao.findCursoByTerm(term);
	}

	@Override
	public void setOfert(boolean isOfert, Integer id) {
		boolean p = dao.existsById(id);
		if (!p) {
			throw new NotFoundException("No existe el producto");
		}
		dao.updateOferta(isOfert, id);
	}

	@Override
	public boolean CheckStock(Integer idProducto, Integer FrontStock) {
		Optional<Producto> p = dao.findById(idProducto);
		if (p.isEmpty()) {
			throw new NotFoundException("El producto no existe para validar stock");
		}
		return p.get().hasStock(FrontStock);
	}

	@Override
	public boolean reduceStock(Integer idProducto, Integer frontStock) {
		// TODO Auto-generated method stub
		return false;
	}

}
