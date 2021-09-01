package com.hotel.model.dao;

import com.hotel.model.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoDao extends JpaRepository<Empleado, Long> {
	
}
