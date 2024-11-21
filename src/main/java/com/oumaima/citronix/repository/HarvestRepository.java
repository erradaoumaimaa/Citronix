package com.oumaima.citronix.repository;

import com.oumaima.citronix.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
}
