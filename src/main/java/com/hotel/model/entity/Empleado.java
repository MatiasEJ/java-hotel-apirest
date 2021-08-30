package com.hotel.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name="empleados")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
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
	private Date fechaNacimiento;

}
