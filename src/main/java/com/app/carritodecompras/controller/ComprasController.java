package com.app.carritodecompras.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.carritodecompras.entities.Compras;
import com.app.carritodecompras.exceptions.BadRequestException;
import com.app.carritodecompras.generics.controller.GenericController;
import com.app.carritodecompras.services.ComprasService;


@CrossOrigin(origins = {"http://localhost:4200/", "*"})
@RestController
@RequestMapping(path = "/api/compras")
public class ComprasController extends GenericController<Compras, Integer, ComprasService> {

	@PostMapping("/pagar")
	public ResponseEntity<?> filtrar(@Valid @RequestBody Compras compra, BindingResult result) {
		if (compra.getDetalles().isEmpty()) {
			throw new BadRequestException("La lista de detalles esta vacia");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarCompra(compra));
	}
}
