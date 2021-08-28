package com.hotel.model.service;

import com.hotel.model.entity.Empleado;
import java.util.List;

public interface EmpleadoService {
	List<Empleado> findAll();	
  Empleado findEmpleado(Empleado empleado);
  Empleado save(Empleado empleado);
  void delete(Empleado empleado);



}
