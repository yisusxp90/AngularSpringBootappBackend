package com.yisusxp.spring.backend.api.service;

import com.yisusxp.spring.backend.api.model.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);
}
