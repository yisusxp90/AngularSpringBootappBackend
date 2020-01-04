package com.yisusxp.spring.backend.api.service;

import com.yisusxp.spring.backend.api.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Cliente save(Cliente cliente);

    public void delete(Long id);

    public Cliente findById(Long id);

    List<Cliente> findByNombreContainingIgnoreCase(String termino);
}
