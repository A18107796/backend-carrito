package com.app.carritodecompras.services;

import java.util.List;
import java.util.Observable;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.carritodecompras.dao.ProductoDAO;
import com.app.carritodecompras.entities.Producto;
import com.app.carritodecompras.exceptions.BadRequestException;
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
	@Transactional
	public boolean CheckStock(Integer idProducto, Integer FrontStock) {
		Optional<Producto> p = dao.findById(idProducto);
		if (p.isEmpty()) {
			throw new NotFoundException("El producto no existe para validar stock");
		}

		if (!p.get().hasStock(FrontStock)) {
			throw new BadRequestException("No hay stock suficiente");
		}

		int res = dao.updateStock(p.get().getStockReducido(FrontStock), idProducto);
		if (res > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean reduceStock(Integer idProducto, Integer frontStock) {

		if (!CheckStock(idProducto, frontStock)) {
			throw new BadRequestException("No hay stock suficiente");
		}

		return false;
	}

}
