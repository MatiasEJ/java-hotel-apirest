package com.hotel.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "habitaciones")
public class Habitacion extends BaseEntity{
    
    @Column(nullable = false)
    private String   num_id;
    private TipoHab  tipo_hab;
    private Tematica tematica;
    @Column(nullable = false)
    private double   precio_noche;
    
    public Habitacion(double precio_noche, String num_id) {
        this.precio_noche = precio_noche;
        this.num_id = num_id;
    }
    
    @Builder
    public Habitacion(Long id,double precio_noche) {
        super(id);
        this.precio_noche = precio_noche;
    }
}
