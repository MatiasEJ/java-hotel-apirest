package com.hotel.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "habitaciones")
public class Habitacion extends BaseEntity{
    
    @Column(nullable = false)
    private String         num_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_hab_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private TipoHabitacion tipo_hab;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tematica_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Tematica       tematica;
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
