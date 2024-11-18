package com.oumaima.citronix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "trees")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "planting_date", nullable = false, updatable = false)
    private LocalDate plantingDate;

    @Transient
    private int age;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

}
