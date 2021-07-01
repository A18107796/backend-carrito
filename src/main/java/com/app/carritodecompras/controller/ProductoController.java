package com.app.carritodecompras.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.carritodecompras.entities.Producto;
import com.app.carritodecompras.exceptions.BadRequestException;
import com.app.carritodecompras.exceptions.InternalServerError;
import com.app.carritodecompras.exceptions.NotFoundException;
import com.app.carritodecompras.generics.controller.GenericController;
import com.app.carritodecompras.services.ProductoService;
import com.app.carritodecompras.services.UploadFileService;
import com.fasterxml.jackson.core.sym.Name;

@CrossOrigin(origins = {"http://localhost:4200/", "*"})
@RestController
@RequestMapping(path = "/api/productos")
public class ProductoController extends GenericController<Producto, Integer, ProductoService> {

	@Autowired
	private UploadFileService uploadService;

	public ProductoController() {
		this.type = Producto.class;
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable(name = "term") String term,
			@RequestParam(name = "oferta") boolean ofert) {
		System.out.println(term);
		if (term.length() == 0) {
			return ResponseEntity.ok(service.findAll());
		}
		return ResponseEntity.ok(service.findCursoByTerm(term + "", ofert));
	}

	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Producto producto, BindingResult result,
			@RequestParam(name = "file") MultipartFile foto) {

		if (!foto.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiarImg(foto);
			} catch (IOException e) {
				throw new InternalServerError(e.getCause() + " " + e.getMessage());
			}
			producto.setFoto(nombreArchivo);
		}
		return super.crear(producto, result);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> update(@Valid Producto producto, BindingResult result,
			@PathVariable("id") Integer id_curso, @RequestParam(name = "file", required = false) MultipartFile foto) {
		Map<String, Object> response = new HashMap<>();

		Optional<Producto> o = service.findById(id_curso);
		if (!o.isPresent()) {
			throw new NotFoundException("El producto no existe.");
		}

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
			response.put("errors", errors);
			throw new BadRequestException(errors);
		}

		Producto productoDB = o.get();
		productoDB.setNombres(producto.getNombres());
		productoDB.setDescripcion(producto.getDescripcion());
		productoDB.setPrecio(producto.getPrecio());
		productoDB.setStock(producto.getStock());
		if (foto != null && !foto.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiarImg(foto);
			} catch (IOException e) {
				throw new InternalServerError(e.getCause() + " " + e.getMessage());
			}
			String nombreFotoAnterior = productoDB.getFoto();
			uploadService.eliminar(nombreFotoAnterior);
			productoDB.setFoto(nombreArchivo);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(productoDB));

	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

		Resource recurso = null;

		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		// el 2do campo fuerza a descargar el atributo
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id) {
		Optional<Producto> o = service.findById(id);

		if (o.isEmpty()) {
			throw new NotFoundException("El/La " + getClassName() + " no existe");
		}
		try {
			String nombreFotoAnterior = o.get().getFoto();
			uploadService.eliminar(nombreFotoAnterior);
			service.deleteById(id);
		} catch (DataAccessException e) {
			throw new InternalServerError(e.getMostSpecificCause().toString());
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}/updateOferta/{status}")
	public ResponseEntity<?> updateOferta(@PathVariable("id") Integer id, @PathVariable("status") boolean estado) {
		service.setOfert(estado, id);
		return ResponseEntity.noContent().build();
	}

}
