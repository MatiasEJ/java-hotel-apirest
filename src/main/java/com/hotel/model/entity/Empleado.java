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

import lombok.*;
import org.hibernate.collection.internal.PersistentSortedMap;

@Getter
@Setter
@Entity
@Table(name="empleados")
public class Empleado extends Persona {

    private String foto;
}
