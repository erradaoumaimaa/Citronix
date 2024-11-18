package com.oumaima.citronix.dto.farm;
import com.oumaima.citronix.utils.validation.OnCreate;
import jakarta.validation.constraints.*;

public record FarmRequestDTO(

        @NotBlank(message = "Nom obligatoire.", groups = OnCreate.class)
        @Size(max = 100, message = "Nom trop long.")
        String name,

        @NotBlank(message = "Localisation obligatoire.", groups = OnCreate.class)
        @Size(max = 200, message = "Localisation trop longue.")
        String location,

        @Positive(message = "Superficie positive.")
        @DecimalMin(value = "0.1", inclusive = true, message = "Min 0.1 hectare.")
        @DecimalMax(value = "10000.0", inclusive = true, message = "Max 10000 hectares.")
        Double totalarea
) {}

