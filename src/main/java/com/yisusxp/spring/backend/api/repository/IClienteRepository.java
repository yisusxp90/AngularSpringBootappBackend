package com.yisusxp.spring.backend.api.repository;

import com.yisusxp.spring.backend.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente> findByDniContainingIgnoreCase(String termino);
}
