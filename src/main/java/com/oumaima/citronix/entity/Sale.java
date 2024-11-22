package com.oumaima.citronix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Positive
    private double unitPrice;

    @NotBlank
    private String client;

    @Column(name = "sale_Date", nullable = false, updatable = false)
    private LocalDate saleDate;


    @PrePersist
    protected void onCreate() {
        this.saleDate = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

}
