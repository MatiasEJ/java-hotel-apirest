package com.hotel.model.dao;

import com.hotel.model.entity.Habitacion;
import org.springframework.data.repository.CrudRepository;

public interface HabitacionDao extends CrudRepository<Habitacion, Long> {
}
