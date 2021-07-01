package com.app.carritodecompras.generics.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.carritodecompras.exceptions.BadRequestException;
import com.app.carritodecompras.exceptions.NotFoundException;
import com.app.carritodecompras.generics.service.GenericService;

public class GenericController<Entity, Key, Service extends GenericService<Entity, Key>> {

	@Autowired
	protected Service service;

	protected Class<Entity> type;

	@GetMapping
	private ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/pagina")
	protected ResponseEntity<?> listar(Pageable pageable) {
		return ResponseEntity.ok().body(service.findAll(pageable));
	}

	@GetMapping("/{id}")
	protected ResponseEntity<?> buscarPorID(@PathVariable("id") Key id) {
		Optional<Entity> o = service.findById(id);
		Map<String, Object> resp = new HashMap<String, Object>();
		if (o.isEmpty()) {
			resp.put("error", "El ID no existe");
			throw new NotFoundException("El " + getClassName() + " no existe");
		}

		resp.put("mensaje:", getClassName() + " encontrado");
		resp.put(getClassName(), o.get());
		return ResponseEntity.ok(resp);
	}

	@PostMapping
	protected ResponseEntity<?> crear(@Valid @RequestBody Entity entity, BindingResult result) {
		Entity entityCreated;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			errors = result.getFieldErrors().stream().map(err -> "'" + err.getField() + "': " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			throw new BadRequestException(errors);
		}
		entityCreated = service.save(entity);

		return ResponseEntity.status(HttpStatus.CREATED).body(entityCreated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Key id) {
		Optional<Entity> o = service.findById(id);

		if (o.isEmpty()) {
			throw new NotFoundException("El/La " + getClassName() + " no existe");
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	protected String getClassName() {
		return type.getSimpleName().toLowerCase();
	}

}
