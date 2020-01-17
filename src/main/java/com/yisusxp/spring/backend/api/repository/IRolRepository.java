package com.yisusxp.spring.backend.api.repository;

import com.yisusxp.spring.backend.api.model.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Role, Long> {

	public List<Role> findAll();
}
