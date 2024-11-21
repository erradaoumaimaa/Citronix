package com.oumaima.citronix.dto.field;

import com.oumaima.citronix.utils.validation.OnCreate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;

public record FieldRequestDTO(
        @Positive(message = "Superficie positive.", groups = OnCreate.class)
        @DecimalMin(value = "0.1", inclusive = true, message = "Min 0.1 hectare.")
        Double area
){}
