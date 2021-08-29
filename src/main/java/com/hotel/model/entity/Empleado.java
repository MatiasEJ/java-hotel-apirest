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
import lombok.Data;

@Data
@Entity
@Table(name="empleados")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;	
	@Column(nullable = false)
	private String apellido;	
	private String dni;	
	@Column(nullable = false)
	private String direccion;	
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

}
