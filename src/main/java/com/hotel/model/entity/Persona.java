package com.hotel.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Persona extends BaseEntity {
    
    @NotEmpty
    @Size(min=2,max=15)
    @Column(nullable = false)
    private String nombre;
    @NotEmpty
    @Size(min=2,max=15)
    @Column(nullable = false)
    private String apellido;
    @NotEmpty
    @Size(min=8,max=8)
    private String dni;
    @Column(nullable = false)
    private String direccion;
    @Temporal(TemporalType.DATE)
    private Date   fechaNacimiento;
}
