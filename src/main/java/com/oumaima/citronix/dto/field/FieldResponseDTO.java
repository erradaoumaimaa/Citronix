package com.oumaima.citronix.dto.field;

import com.oumaima.citronix.dto.farm.FarmResponseDTO;

public record FieldResponseDTO(
        Long id,
        Double area,
        FarmResponseDTO farm
) {}