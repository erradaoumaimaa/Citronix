package com.oumaima.citronix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "trees")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "planting_date", nullable = false, updatable = false)
    private LocalDate plantingDate;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @PrePersist
    @PreUpdate
    public void validateTreeConstraints() {
        if (plantingDate == null) {
            throw new IllegalArgumentException("La date de plantation ne peut pas être null.");
        }
        if (plantingDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date de plantation ne peut pas être dans le futur.");
        }
        if (!isPlantingSeason()) {
            throw new IllegalArgumentException("La plantation ne peut être effectuée qu'entre mars et mai.");
        }
    }

    @Transient
    public int getAge() {
        return plantingDate != null ? LocalDate.now().getYear() - plantingDate.getYear() : 0;
    }

    @Transient
    public double getAnnualProductivity() {
        int age = getAge();
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else if (age <= 20) {
            return 20.0;
        }
        return 0.0;
    }

    @Transient
    public boolean isPlantingSeason() {
        if (plantingDate == null) {
            return false;
        }
        int month = plantingDate.getMonthValue();
        return month >= 3 && month <= 5;
    }
}
