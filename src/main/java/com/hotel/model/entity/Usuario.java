package com.hotel.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity{
    @Column(unique = true,length = 25)
    private String username;
    private String password;
    private Boolean enabled;
    
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","rol_id"})})
    private List<Rol> roles;
}
