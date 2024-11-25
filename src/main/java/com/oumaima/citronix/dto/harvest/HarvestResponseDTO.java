package com.oumaima.citronix.dto.harvest;

import com.oumaima.citronix.dto.field.FieldResponseDTO;
import com.oumaima.citronix.entity.enums.Season;
import java.time.LocalDate;

public record HarvestResponseDTO(
        Long id,
        LocalDate harvestDate,
        Season season,
        FieldResponseDTO field,
        double totalQuantity
) {}
