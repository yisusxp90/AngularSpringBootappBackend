package com.yisusxp.spring.backend.api.repository;

import com.yisusxp.spring.backend.api.model.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends CrudRepository<Factura, Long> {
}
