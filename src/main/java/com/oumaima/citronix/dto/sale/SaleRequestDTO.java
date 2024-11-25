package com.oumaima.citronix.dto.sale;

import com.oumaima.citronix.utils.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record SaleRequestDTO(
        @NotBlank(message = "Le client est obligatoire.",groups = OnCreate.class)
        String client,

        @Positive(message = "La quantité doit être un nombre positif.")
        Double quantity,

        @Positive(message = "Le prix unitaire doit être un nombre positif.")
                Double unitPrice
) {
}
