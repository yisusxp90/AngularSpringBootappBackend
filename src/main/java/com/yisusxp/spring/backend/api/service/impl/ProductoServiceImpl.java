package com.yisusxp.spring.backend.api.service.impl;

import com.yisusxp.spring.backend.api.model.Producto;
import com.yisusxp.spring.backend.api.repository.IProductoRepository;
import com.yisusxp.spring.backend.api.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository iProductoRepository;

    @Override
    public List<Producto> findByNombre(String termino) {
        return iProductoRepository.findByNombre(termino);
    }

    @Override
    public List<Producto> findByNombreContainingIgnoreCase(String termino) {
        return iProductoRepository.findByNombreContainingIgnoreCase(termino);
    }

    @Override
    public List<Producto> findByNombreStartingWithIgnoreCase(String termino) {
        return iProductoRepository.findByNombreStartingWithIgnoreCase(termino);
    }
}
