package com.yisusxp.spring.backend.api.service.impl;

import com.yisusxp.spring.backend.api.model.Factura;
import com.yisusxp.spring.backend.api.repository.IFacturaRepository;
import com.yisusxp.spring.backend.api.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaServiceImpl implements IFacturaService {

    @Autowired
    private IFacturaRepository iFacturaRepository;

    @Transactional(readOnly = true)
    @Override
    public Factura findById(Long id) {
        return iFacturaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Factura save(Factura factura) {
        return iFacturaRepository.save(factura);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        iFacturaRepository.deleteById(id);
    }
}
