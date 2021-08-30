package com.hotel.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tematicas")
public class Tematica extends BaseHabitaciones {
}
