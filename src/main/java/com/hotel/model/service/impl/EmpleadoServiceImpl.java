package com.hotel.model.service.impl;

import com.hotel.model.dao.EmpleadoDao;
import com.hotel.model.entity.Empleado;
import com.hotel.model.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    
    @Autowired
    private EmpleadoDao empleadoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Empleado findById(Long id) {
        return empleadoDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public Empleado save(Empleado empleado) {
        return empleadoDao.save(empleado);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        empleadoDao.deleteById(id);
    }
    
}