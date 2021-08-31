package com.hotel.controller;

import com.hotel.model.entity.Empleado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.hotel.model.service.impl.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {

	@Autowired
	private EmpleadoServiceImpl service;

	@GetMapping(EmpleadoUri.EMPLEADOS)
	public List<Empleado> index() {
		return service.findAll();
	}

	@GetMapping(EmpleadoUri.EMPLEADO_ID)
	public ResponseEntity<?> findEmpleadoById(@PathVariable Long id) {
		Empleado empleado;
		Map<String, Object> response = new HashMap<>();
		try {
			empleado = service.findById(id);
		} catch (DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta"
					.concat(": ")
					.concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (empleado == null) {
			response.put("mensaje", "El empleado de ID: "
					.concat(id.toString())
					.concat(" no existe en la base de datos."));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(empleado, HttpStatus.OK);
	}

	@PutMapping(EmpleadoUri.EMPLEADO_ID)
	public ResponseEntity<?> updateById(@Valid @RequestBody Empleado empleado, BindingResult result, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Empleado empleadoActual = service.findById(id);
		Empleado empleadoActualizado = null;

		if (result.hasErrors()) {
		return handleError(result, response);
		}

		if (empleadoActual == null) {
			return handleError(result, response);
		}

		try {
			empleadoActual.setNombre(empleado.getNombre());
			empleadoActual.setApellido(empleado.getApellido());
			empleadoActual.setDni(empleado.getDni());
			empleadoActual.setDireccion(empleado.getDireccion());
			empleadoActual.setFechaNacimiento(empleado.getFechaNacimiento());
			empleadoActualizado = service.save(empleadoActual);

		} catch (DataAccessException ex) {
			response.put("mensaje", "Error al actualizar: "
					.concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Empleado actualizado con exito: ");
		response.put("empleado", empleadoActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}


	@PostMapping(EmpleadoUri.EMPLEADO)
	public ResponseEntity<?> create(@Valid @RequestBody Empleado empleado, BindingResult result) {
		Empleado nuevoEmpleado = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
		return handleError(result, response);
		}

		try {
			nuevoEmpleado = service.save(empleado);
		} catch (DataAccessException ex) {
			response.put("mensaje", "Error al realizar INSERT"
					.concat(": ")
					.concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Empleado creado con exito.");
		response.put("empleado", nuevoEmpleado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}


	@DeleteMapping(EmpleadoUri.EMPLEADO_ID)
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			service.deleteById(id);
		} catch (DataAccessException ex) {
			response.put("mensaje", "Error al realizar DELETE"
					.concat(": ")
					.concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Empleado borrado con exito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	private ResponseEntity<?> handleError(BindingResult result, Map<String, Object> response) {
		List<String> errors = result.getFieldErrors().stream()
				.map(error -> "El campo: " + error.getField() + " " + error.getDefaultMessage())
				.collect(Collectors.toList());
		
		response.put("errors", errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
