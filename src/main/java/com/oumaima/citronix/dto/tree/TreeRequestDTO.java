package com.oumaima.citronix.dto.tree;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TreeRequestDTO(
        @NotNull(message = "La date de plantation ne peut pas être null")
        LocalDate plantingDate
) {}
