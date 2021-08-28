package com.hotel.controller;

import com.hotel.model.entity.Empleado;
import com.hotel.model.service.EmpleadoServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
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
	public Empleado findEmpleadoById(Empleado empleado) {
		return service.findEmpleado(empleado);
	}

	@PutMapping(EmpleadoUri.EMPLEADO_ID)
	public Empleado update(@RequestBody Empleado empleado){
		Empleado empleadoActual = service.findEmpleado(empleado);
		empleadoActual.setNombre(empleado.getNombre());
		empleadoActual.setApellido(empleado.getApellido());
		empleadoActual.setDni(empleado.getDni());
		empleadoActual.setDireccion(empleado.getDireccion());
		empleadoActual.setFechaNacimiento(empleado.getFechaNacimiento());

		return service.save(empleadoActual);
	} 

	@PostMapping(EmpleadoUri.EMPLEADO)
	@ResponseStatus(HttpStatus.CREATED)
	public Empleado create(@RequestBody Empleado empleado) {
		return service.save(empleado);
	}

	@PostMapping(EmpleadoUri.EMPLEADO_ID)
	public void delete(Empleado empleado) {
		service.delete(empleado);
	}

}
