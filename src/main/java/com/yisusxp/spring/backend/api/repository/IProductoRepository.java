package com.yisusxp.spring.backend.api.repository;

import com.yisusxp.spring.backend.api.model.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IProductoRepository extends CrudRepository<Producto, Long> {

    @Transactional
    @Query(value="select p from productos where p.name like %?1%", nativeQuery = true)
    public List<Producto> findByNombre(String termino);

    // buscamos por nombre que contenga el termino ignorando si es mayuscula o minuscula
    public List<Producto> findByNombreContainingIgnoreCase(String termino);

    // buscamos por nombre que comience con el termino ignorando si es mayuscula o minuscula
    public List<Producto> findByNombreStartingWithIgnoreCase(String termino);
}
