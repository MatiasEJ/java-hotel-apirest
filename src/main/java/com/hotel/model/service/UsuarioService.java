package com.hotel.model.service;

import com.hotel.model.entity.Usuario;

public interface UsuarioService {
    Usuario findByUsername(String username);
}
