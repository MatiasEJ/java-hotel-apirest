package com.hotel.model.service.impl;

import com.hotel.model.dao.UsuarioDao;
import com.hotel.model.entity.Empleado;
import com.hotel.model.entity.Usuario;
import com.hotel.model.service.EmpleadoService;
import com.hotel.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }
    
    @Override
    public Usuario findEmpleadoById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }
    
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }
    
    @Override
    public void deleteById(Long id) {
       usuarioDao.deleteById(id);
    }
}
