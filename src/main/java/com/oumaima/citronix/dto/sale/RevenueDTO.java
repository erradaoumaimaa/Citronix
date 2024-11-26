package com.oumaima.citronix.dto.sale;

import java.util.List;

public record RevenueDTO(double totalRevenue,
                         List<SaleResponseDTO> sales) {
}
