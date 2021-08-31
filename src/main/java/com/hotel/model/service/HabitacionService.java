package com.hotel.model.service;

import com.hotel.model.entity.Habitacion;

import java.util.List;

public interface HabitacionService {
    List<Habitacion> findAll();
    Habitacion find(Habitacion habitacion);
    Habitacion save(Habitacion habitacion);
    void delete(Long id);
}
