package com.oumaima.citronix.dto.sale;

import java.time.LocalDate;

public record SaleResponseDTO(
        Long id,
        String client,
        LocalDate saleDate,
        Double quantity,
        Double unitPrice,
        Long harvestId,
        Double revenue
) {
}
