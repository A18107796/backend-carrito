package com.app.carritodecompras.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.carritodecompras.entities.Compras;
import com.app.carritodecompras.entities.Producto;
import com.app.carritodecompras.exceptions.BadRequestException;
import com.app.carritodecompras.exceptions.InternalServerError;
import com.app.carritodecompras.exceptions.NotFoundException;
import com.app.carritodecompras.generics.controller.GenericController;
import com.app.carritodecompras.services.ComprasService;
import com.app.carritodecompras.services.UploadFileService;

@CrossOrigin(origins = { "http://localhost:4200/", "*" })
@RestController
@RequestMapping(path = "/api/compras")
public class ComprasController extends GenericController<Compras, Integer, ComprasService> {
	public ComprasController() {
		this.type = Compras.class;
	}

	@Autowired
	private UploadFileService uploadService;

	@PostMapping("/pagar")
	public ResponseEntity<?> filtrar(@Valid @RequestBody Compras compra, BindingResult result) {
		if (compra.getDetalles().isEmpty()) {
			throw new BadRequestException("La lista de detalles esta vacia");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarCompra(compra));
	}

	@GetMapping("/filtrar/ganancias")
	public ResponseEntity<?> findGananciasXFecha(
			@RequestParam(name = "fecha_inicio", defaultValue = "") String fecha_inicio,
			@RequestParam(name = "fecha_fin", defaultValue = "") String fecha_fin) {
		Map<String, Double> response;
		System.out.println(fecha_inicio);
		System.out.println(fecha_fin);
		Double gananciasTotales = service.getGanancias(fecha_inicio, fecha_fin);
		if (gananciasTotales == null) {
			gananciasTotales = 0.0;
		}
		response = new HashMap<>();
		response.put("ganancias", gananciasTotales);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/filtrar/ganancias/total")
	public ResponseEntity<?> findGananciasXFecha() {
		Map<String, Double> response;
		Double gananciasTotales = service.getGananciasTotales();
		if (gananciasTotales == null) {
			gananciasTotales = 0.0;
		}
		response = new HashMap<>();
		response.put("ganancias", gananciasTotales);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/upload/{id}")
	public ResponseEntity<?> uploadRecibo(@PathVariable("id") Integer id_curso,
			@RequestParam(name = "img", required = false) MultipartFile foto) {

		Optional<Compras> cDB = this.service.findById(id_curso);
		if (cDB.isEmpty()) {
			throw new NotFoundException("No existe la compra, verifique ID");
		}

		if (cDB.get().getImg() != null && cDB.get().getImg().length() > 1) {
			throw new BadRequestException("El recibo solo se puede subir una vez, si el recibo no es valido se cancelara el pedido.");
		}
		if (foto != null && !foto.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiarImg(foto);
			} catch (IOException e) {
				throw new InternalServerError(e.getCause() + " " + e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(this.service.updateFoto(nombreArchivo, id_curso));
		} else {
			throw new NotFoundException("No envio la foto");
		}

	}

	@PutMapping("/update/{id}/{estado}")
	public ResponseEntity<?> updateEstado(@PathVariable("id") Integer id_curso, @PathVariable("estado") String estado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.updateEstado(estado, id_curso));
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
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable(name = "term") String term) {
		return ResponseEntity.ok(service.findByLike(term));
	}


}
