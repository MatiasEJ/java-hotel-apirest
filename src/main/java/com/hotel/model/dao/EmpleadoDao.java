package com.hotel.model.dao;

import com.hotel.model.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoDao extends CrudRepository<Empleado, Long> {
	
}
