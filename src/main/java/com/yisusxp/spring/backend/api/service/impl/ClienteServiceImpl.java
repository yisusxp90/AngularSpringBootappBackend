package com.yisusxp.spring.backend.api.service.impl;

import com.yisusxp.spring.backend.api.model.Cliente;
import com.yisusxp.spring.backend.api.repository.IClienteRepository;
import com.yisusxp.spring.backend.api.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository iClienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) iClienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return iClienteRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return iClienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        iClienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return iClienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> findByDniContainingIgnoreCase(String termino) {
        return iClienteRepository.findByDniContainingIgnoreCase(termino);
    }
}
