package com.oumaima.citronix.repository;

import com.oumaima.citronix.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    Optional<Farm> findByName(String name);

    Optional<Farm> findById(Long id);
    List<Farm> findByLocation(String location);
}
