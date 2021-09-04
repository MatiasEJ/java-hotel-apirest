package com.hotel.model.dao;

import com.hotel.model.entity.Habitacion;
import com.hotel.model.entity.Tematica;
import com.hotel.model.entity.TipoHabitacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HabitacionDao extends CrudRepository<Habitacion, Long> {
    @Query("from Tematica")
    List<Tematica> findAllTematicas();
    @Query("from TipoHabitacion")
    List<TipoHabitacion> findAllTipoHabitacion();
}
