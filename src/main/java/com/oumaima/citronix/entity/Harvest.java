package com.oumaima.citronix.entity;


import com.oumaima.citronix.entity.enums.Season;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "harvests")
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "season", nullable = false)
    private Season season;

    @Column(name = "harvest_Date", nullable = false, updatable = false)
    private LocalDate harvestDate;

    @PositiveOrZero
    private double totalQuantity;


    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<HarvestDetail> harvestDetails;

    @OneToMany(mappedBy = "harvest")
    private List<Sale> sales;
}
