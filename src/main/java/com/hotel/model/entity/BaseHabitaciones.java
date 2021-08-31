package com.hotel.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseHabitaciones implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private static final long serialVersionUID = 1L;
    
    public Long getId() {
        return id;
    }
    private String nombre;
    private String descrip;

}
