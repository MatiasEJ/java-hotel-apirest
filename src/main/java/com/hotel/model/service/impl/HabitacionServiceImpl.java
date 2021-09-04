package com.hotel.model.service.impl;

import com.hotel.model.dao.HabitacionDao;
import com.hotel.model.entity.Habitacion;
import com.hotel.model.entity.Tematica;
import com.hotel.model.entity.TipoHabitacion;
import com.hotel.model.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HabitacionServiceImpl implements HabitacionService {
    
    @Autowired
    private HabitacionDao habitacionDao;
    
    public HabitacionServiceImpl(HabitacionDao habitacionDao) {
        this.habitacionDao = habitacionDao;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Habitacion> findAll() {
        return (List<Habitacion>)habitacionDao.findAll();
    }
    
    @Override
    public Habitacion findHabitacionById(Long id) {
        return habitacionDao.findById(id).orElse(null);
    }
    
    @Override
    public Habitacion save(Habitacion habitacion) {
        return habitacionDao.save(habitacion);
    }
    
    @Override
    public List<Tematica> findAllTematicas() {
        return habitacionDao.findAllTematicas();
    }
    
    @Override
    public List<TipoHabitacion> findAllTipoHabitacion() {
        return habitacionDao.findAllTipoHabitacion();
    }
    
    @Override
    public void delete(Long id) {
       habitacionDao.deleteById(id);
    }
}
