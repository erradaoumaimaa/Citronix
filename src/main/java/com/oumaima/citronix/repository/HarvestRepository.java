package com.oumaima.citronix.repository;

import com.oumaima.citronix.entity.Field;
import com.oumaima.citronix.entity.Harvest;
import com.oumaima.citronix.entity.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {

    @Query("SELECT CASE WHEN COUNT(h) > 0 " +
            "THEN true ELSE false END FROM Harvest h" +
            " WHERE h.field = :field AND h.season = :season " +
            "AND YEAR(h.harvestDate) = :year")
    boolean existsByFieldAndSeasonAndHarvestDateYear(@Param("field") Field field,
                                                     @Param("season") Season season,
                                                     @Param("year") int year);

    List<Harvest> findBySeason(Season season);
}

