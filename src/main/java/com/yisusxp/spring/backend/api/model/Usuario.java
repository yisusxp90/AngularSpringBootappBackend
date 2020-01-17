package com.yisusxp.spring.backend.api.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = -8172977627699996879L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, length = 20)
    @NotEmpty(message = "No puede ser vacio, favor ingrese el campo")
    @Size(min = 4, max = 20, message = "debe tener una longitud entre 4 y 20 caracteres")
    private String username;
    
    @Column(nullable = false, length = 60) // por ser encriptado y por ello mas grande que desde form
    private String password;
    
    @Column(nullable = false)
    private Boolean enabled = Boolean.TRUE;
    
    @Column(nullable = false)
    @NotEmpty(message = "No puede ser vacio, favor ingrese el campo")
    @Size(min = 4, max = 12, message = "debe tener una longitud entre 4 y 12 caracteres")
    private String nombre;
    
    @Column(nullable = false)
    @NotEmpty(message = "No puede ser vacio, favor ingrese el campo")
    private String apellido;
    
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "No puede ser vacio, favor ingrese el campo")
    @Email(message = "debe tener un formato de email valido")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
