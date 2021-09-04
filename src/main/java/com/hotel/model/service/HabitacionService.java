package com.hotel.model.service;

import com.hotel.model.entity.Habitacion;
import com.hotel.model.entity.Tematica;
import com.hotel.model.entity.TipoHabitacion;

import java.util.List;

public interface HabitacionService {
    List<Habitacion> findAll();
    Habitacion findHabitacionById(Long id);
    Habitacion save(Habitacion habitacion);
    void delete(Long id);
    List<Tematica> findAllTematicas();
    List<TipoHabitacion> findAllTipoHabitacion();
}
