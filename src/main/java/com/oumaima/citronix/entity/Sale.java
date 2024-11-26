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

    @Transient
    @With
    private double revenue;

    @NotBlank
    @NonNull
    private String client;

    @Column(name = "sale_Date", nullable = false, updatable = false)
    private LocalDate saleDate;

    @Positive
    @NonNull
    private double quantity;

    @PrePersist
    protected void onCreate() {
        this.saleDate = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    @NonNull
    private Harvest harvest;

}
