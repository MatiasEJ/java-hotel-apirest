package com.hotel.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="tipos_habitacion")
public class TipoHab extends BaseHabitaciones {
    private int cant_huespedes;
    
}