package com.yisusxp.spring.backend.api.repository;

import com.yisusxp.spring.backend.api.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
}
