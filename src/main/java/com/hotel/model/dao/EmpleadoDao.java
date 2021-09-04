package com.hotel.model.dao;

import com.hotel.model.entity.Empleado;
import com.hotel.model.entity.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadoDao extends JpaRepository<Empleado, Long> {
}
