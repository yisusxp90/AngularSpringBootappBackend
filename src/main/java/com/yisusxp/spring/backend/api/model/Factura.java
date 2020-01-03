package com.yisusxp.spring.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {
    private static final long serialVersionUID = 1965152564398966153L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "No puede ser vacio, favor ingrese el campo")
    private String descripcion;
    @Column
    private String observacion;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"facturas", "hibernateLazyInitializer", "handler"}) //ignoramos la rlacion inversa para evitar looks infinitos
    private  Cliente cliente;
    // cascade all si borramos una factura se borran todos los items dependientes
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id") // se coloca ya que solo la crea automatica cuando es manyToOne
    @NotNull(message = "No debe ser vacio, favor ingrese el campo")
    @NotEmpty(message = "No debe ser vacio, favor ingrese el campo")
    private List<ItemFactura> items;

    public Factura() {
        this.items = new ArrayList<>();
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Double getTotal(){
        Double total = 0.0;
        for(ItemFactura item : items){
            total += item.getImporte();
        }
        return total;
    }
}
