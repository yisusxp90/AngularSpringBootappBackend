package com.yisusxp.spring.backend.api.service;

import java.util.List;

import com.yisusxp.spring.backend.api.model.Role;

public interface IRolService {
	 	public List<Role> findAll();
	 	
	    public Role save(Role rol);	    

	    public void delete(Long id);
	    
	    public Role findById(Long id);   
}
