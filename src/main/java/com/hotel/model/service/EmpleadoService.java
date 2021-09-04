package com.hotel.model.service;

import com.hotel.model.entity.Empleado;
import com.hotel.model.entity.Tematica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> findAll();
    
    Page<Empleado> findAll(Pageable page);
    
    Empleado findById(Long id);
    
    Empleado save(Empleado empleado);
    
    void deleteById(Long id);
    
}
