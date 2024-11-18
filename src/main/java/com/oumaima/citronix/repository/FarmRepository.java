package com.oumaima.citronix.repository;

import com.oumaima.citronix.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    Optional<Farm> findByName(String name);

    Optional<Farm> findById(Long id);
    List<Farm> findByLocation(String location);
}
