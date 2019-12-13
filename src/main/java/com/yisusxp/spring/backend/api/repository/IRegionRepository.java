package com.yisusxp.spring.backend.api.repository;

import com.yisusxp.spring.backend.api.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IRegionRepository extends JpaRepository<Region, Long> {

    @Transactional
    @Query(value="select * from regiones", nativeQuery = true)
    public List<Region> findAllRegiones();
}
