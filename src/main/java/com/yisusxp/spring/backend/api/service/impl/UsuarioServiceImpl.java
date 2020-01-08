package com.yisusxp.spring.backend.api.service.impl;

import com.yisusxp.spring.backend.api.model.Usuario;
import com.yisusxp.spring.backend.api.repository.IUsuarioRepository;
import com.yisusxp.spring.backend.api.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("usuarioDetailService")
public class UsuarioServiceImpl implements UserDetailsService, IUsuarioService {

    @Autowired
    IUsuarioRepository iUsuarioRepository;

    private final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = iUsuarioRepository.findByUsername(username);
        if(usuario == null){
            logger.error("Error login - No existe usuario: " + username);
            throw new UsernameNotFoundException("Error login - No existe usuario: " + username);
        }
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());
        return new User(username, usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return iUsuarioRepository.findByUsername(username);
    }

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) iUsuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {		
		return iUsuarioRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		if (usuario.getEnabled()==null) 
			usuario.setEnabled(Boolean.FALSE);
		
		return iUsuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		iUsuarioRepository.deleteById(id);;
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return iUsuarioRepository.findById(id).orElse(null);
	}
}
