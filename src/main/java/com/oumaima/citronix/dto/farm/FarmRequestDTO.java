package com.oumaima.citronix.dto.farm;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmRequestDTO {

    @NotBlank(message = "Nom obligatoire.")
    @Size(max = 100, message = "Nom trop long.")
    private String name;

    @NotBlank(message = "Localisation obligatoire.")
    @Size(max = 200, message = "Localisation trop longue.")
    private String location;

    @Positive(message = "Superficie positive.")
    @DecimalMin(value = "0.1", inclusive = true, message = "Min 0.1 hectare.")
    @DecimalMax(value = "10000.0", inclusive = true, message = "Max 10000 hectares.")
    private double area;
}
