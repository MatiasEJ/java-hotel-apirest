package com.hotel.model.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "roles")
public class Rol extends BaseEntity {
    private String nombre;
}
