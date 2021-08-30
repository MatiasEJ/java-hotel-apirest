package com.hotel.model.service;

import com.hotel.model.entity.Empleado;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> findAll();
    
    Empleado findById(Long id);
    
    Empleado save(Empleado empleado);
    
    void deleteById(Long id);
    
    
}
