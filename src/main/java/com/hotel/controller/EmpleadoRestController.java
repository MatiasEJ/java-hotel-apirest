package com.hotel.controller;

import com.hotel.model.entity.Empleado;
import com.hotel.model.service.EmpleadoServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {

	@Autowired
	private EmpleadoServiceImpl service; 
	
	@GetMapping("/empleados")
	public List<Empleado> index(){
		return service.findAll();
	}	
	
}
