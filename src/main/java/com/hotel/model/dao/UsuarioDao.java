package com.hotel.model.dao;

import com.hotel.model.entity.Empleado;
import com.hotel.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {
	
}
