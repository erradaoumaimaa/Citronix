package com.oumaima.citronix.repository;

import com.oumaima.citronix.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FarmRepository extends JpaRepository<Farm, UUID> {

}
