package com.hotel.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "habitaciones")
public class Habitacion extends BaseEntity{
    
    private String   num_id;
    private TipoHab  tipo_hab;
    private Tematica tematica;
    private double   precio_noche;
    
}
