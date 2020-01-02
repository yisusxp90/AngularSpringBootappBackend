package com.yisusxp.spring.backend.api.service;

import com.yisusxp.spring.backend.api.model.Factura;

public interface IFacturaService {

    public Factura findById(Long id);

    public Factura save(Factura factura);

    public void delete(Long id);
}
