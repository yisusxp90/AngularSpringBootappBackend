package com.yisusxp.spring.backend.api.service;

import com.yisusxp.spring.backend.api.model.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> findByNombre(String termino);

    List<Producto> findByNombreContainingIgnoreCase(String termino);

    List<Producto> findByNombreStartingWithIgnoreCase(String termino);
}
