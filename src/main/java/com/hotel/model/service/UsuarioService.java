package com.hotel.model.service;

import com.hotel.model.entity.Empleado;
import com.hotel.model.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findEmpleadoById(Long id);
    Usuario save(Usuario empleado);
    void deleteById(Long id);
    
    
}
