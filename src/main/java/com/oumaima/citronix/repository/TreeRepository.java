package com.oumaima.citronix.repository;

import com.oumaima.citronix.entity.Tree;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TreeRepository extends JpaRepository<Tree, Long> {


    long countByFieldId(Long fieldId);


    List<Tree> findAllByFieldId(Long fieldId);


    @Query("SELECT t FROM Tree t JOIN FETCH t.field WHERE t.id = :id")
    Optional<Tree> findByIdWithField(@Param("id") Long id);


    List<Tree> findAllByPlantingDateBetween(LocalDate startDate, LocalDate endDate);


    @Query("SELECT t FROM Tree t WHERE t.plantingDate >= :minDate")
    List<Tree> findAllProductiveTrees(@Param("minDate") LocalDate minDate);


}
