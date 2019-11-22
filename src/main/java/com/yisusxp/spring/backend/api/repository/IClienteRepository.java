package com.yisusxp.spring.backend.api.repository;

import com.yisusxp.spring.backend.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
