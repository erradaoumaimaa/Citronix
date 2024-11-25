package com.oumaima.citronix.dto.field;

import com.oumaima.citronix.utils.validation.OnCreate;
import jakarta.validation.constraints.*;

public record FieldRequestDTO(
        @Positive(message = "Superficie positive.", groups = OnCreate.class)
        @DecimalMin(value = "0.1", inclusive = true, message = "Le Minu 0.1 hectare.")
        Double area
){}
