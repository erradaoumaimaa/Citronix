package com.oumaima.citronix.dto.field;

import com.oumaima.citronix.entity.Farm;

public record FieldResponseDTO(
        Long id,
        Double area,
        Farm farm
) {}
