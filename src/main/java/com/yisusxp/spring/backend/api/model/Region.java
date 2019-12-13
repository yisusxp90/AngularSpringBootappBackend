package com.yisusxp.spring.backend.api.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "regiones")
public class Region implements Serializable {
    private static final long serialVersionUID = 9004588931703572900L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
