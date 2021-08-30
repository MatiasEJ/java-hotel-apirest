package com.hotel.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "habitaciones")
public class Habitacion extends BaseEntity {
    private String num_id;
    private TipoHab tipo_hab;
    private Tematica tematica;
    private double precio_noche;
    
}
