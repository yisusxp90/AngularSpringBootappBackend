package com.yisusxp.spring.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1099454957887947423L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "No puede ser vacio, favor ingrese el campo")
    @Size(min = 4, max = 12, message = "debe tener una longitud entre 4 y 12 caracteres")
    private String dni;
    @Column(nullable = false)
    @NotEmpty(message = "No puede ser vacio, favor ingrese el campo")
    @Size(min = 4, max = 12, message = "debe tener una longitud entre 4 y 12 caracteres")
    private String nombre;
    @Column
    @NotEmpty(message = "No puede ser vacio, favor ingrese el campo")
    private String apellido;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "No puede ser vacio, favor ingrese el campo")
    @Email(message = "debe tener un formato de email valido")
    private String email;
    @NotNull(message = "No puede ser vacio, favor ingrese el campo")
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column
    public String foto;
    @NotNull(message = "No debe ser vacio, favor ingrese el campo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;
    @JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    List<Factura> facturas;

    public Cliente() {
        this.facturas = new ArrayList<>();
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    /*@PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getNombre(), cliente.getNombre()) &&
                Objects.equals(getApellido(), cliente.getApellido()) &&
                Objects.equals(getEmail(), cliente.getEmail()) &&
                Objects.equals(getCreateAt(), cliente.getCreateAt()) &&
                Objects.equals(getRegion().getId(), cliente.getRegion().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getApellido(), getEmail(), getCreateAt(), getRegion());
    }
}
