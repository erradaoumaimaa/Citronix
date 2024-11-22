package com.oumaima.citronix.dto.tree;

import com.oumaima.citronix.dto.field.FieldResponseDTO;
import com.oumaima.citronix.entity.Field;
import java.time.LocalDate;

public record TreeResponseDto(
        Long id,
        LocalDate plantingDate,
        FieldResponseDTO field
) {}
