package com.yisusxp.spring.backend.api.repository;

import com.yisusxp.spring.backend.api.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {

    @Transactional
    @Query(value="select * from usuarios where username=?1", nativeQuery = true)
    public Usuario findByUsername(String username);
}
