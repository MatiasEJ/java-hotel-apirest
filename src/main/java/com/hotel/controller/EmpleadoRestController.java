package com.hotel.controller;

import com.hotel.model.entity.Empleado;
import com.hotel.model.service.EmpleadoServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public Empleado findEmpleadoById(@PathVariable Long id) {
		return service.findEmpleadoById(id);
	}

	@PutMapping(EmpleadoUri.EMPLEADO_ID)
	@ResponseStatus(HttpStatus.CREATED)
	public Empleado updateById(@RequestBody Empleado empleado,@PathVariable Long id){
		Empleado empleadoActual = service.findEmpleadoById(id);
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

	@DeleteMapping(EmpleadoUri.EMPLEADO_ID)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		service.deleteById(id);
	}

}
