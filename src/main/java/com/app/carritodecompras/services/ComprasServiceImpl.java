package com.app.carritodecompras.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.carritodecompras.dao.ComprasDAO;
import com.app.carritodecompras.dao.ProductoDAO;
import com.app.carritodecompras.entities.Compras;
import com.app.carritodecompras.entities.Detalle_compras;
import com.app.carritodecompras.entities.Pago;
import com.app.carritodecompras.entities.Producto;
import com.app.carritodecompras.exceptions.BadRequestException;
import com.app.carritodecompras.exceptions.InternalServerError;
import com.app.carritodecompras.generics.service.GenericServiceImpl;

@Service
public class ComprasServiceImpl extends GenericServiceImpl<Compras, ComprasDAO, Integer> implements ComprasService {

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private ProductoService productoService;

	@Override
	@Transactional
	public Compras guardarCompra(Compras compra) {

		for (Detalle_compras p : compra.getDetalles()) {
			if (!productoService.CheckStock(p.getProducto().getId(), p.getCantidad())) {
				throw new InternalServerError("Ocurrio un error interno, intentelo denuevo :(");
			}
		}
		compra.setEstado("PAGADO - Envio Pendiente");
		compra.setFecha_compras(new Date());
		compra.setCliente(null);
		compra.setPago(null);
		return dao.save(compra);
	}

	@Override
	public Compras cambiarEstadoCompra(Integer idCompra, String estado) {
		// TODO Auto-generated method stub
		return null;
	}

}
