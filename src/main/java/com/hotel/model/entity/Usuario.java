package com.hotel.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity{
    private String username;
    private String password;
}
