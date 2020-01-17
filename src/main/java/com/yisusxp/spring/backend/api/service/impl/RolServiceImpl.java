package com.yisusxp.spring.backend.api.service.impl;

import com.yisusxp.spring.backend.api.auth.SpringSecurityConfig;
import com.yisusxp.spring.backend.api.model.Role;
import com.yisusxp.spring.backend.api.repository.IRolRepository;
import com.yisusxp.spring.backend.api.service.IRolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    IRolRepository iRolRepository;
     
    @Autowired
    SpringSecurityConfig springSecurityConfig;

    private final Logger logger = LoggerFactory.getLogger(RolServiceImpl.class);


	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return (List<Role>) iRolRepository.findAll();
	}

	@Override
	@Transactional
	public Role save(Role rol) {
		return iRolRepository.save(rol);
	}

	public String encode(String passwordClear) {
		String passwordBCrypt = null;
		
		if (passwordClear != null)	
		{
			passwordBCrypt = this.springSecurityConfig.passwordEncoder().encode(passwordClear);			
		}
		return passwordBCrypt;
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		iRolRepository.deleteById(id);;
	}

	@Override
	@Transactional(readOnly = true)
	public Role findById(Long id) {
		return iRolRepository.findById(id).orElse(null);
	}
}
