package com.oumaima.citronix.repository;

import com.oumaima.citronix.entity.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FieldRepository extends JpaRepository<Field,Long> {

    Page<Field> findAllByFarmId(Long farmId, Pageable pageable);
}
