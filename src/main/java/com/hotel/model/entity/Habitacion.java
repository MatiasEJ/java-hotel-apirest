package com.hotel.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "habitaciones")
public class Habitacion extends BaseEntity {
    private String numeroIden;
    private String nombre;
    private TipoHab tipoHab;
    private Tematica tematica;
    private double precioNoche;
    
}
