package com.yisusxp.spring.backend.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yisusxp.spring.backend.api.model.Usuario;

public interface IUsuarioService {
	 	public List<Usuario> findAll();

	    public Page<Usuario> findAll(Pageable pageable);

	    public Usuario save(Usuario cliente);

	    public void delete(Long id);
	    
	    public Usuario findById(Long id);

    public Usuario findByUsername(String username);
}
