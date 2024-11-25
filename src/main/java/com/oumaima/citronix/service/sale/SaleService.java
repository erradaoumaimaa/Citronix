package com.oumaima.citronix.service.sale;

import com.oumaima.citronix.dto.sale.SaleRequestDTO;
import com.oumaima.citronix.dto.sale.SaleResponseDTO;

import java.util.List;

public interface SaleService {

    SaleResponseDTO createSale(Long harvestId, SaleRequestDTO saleRequestDTO);

    List<SaleResponseDTO> getAllSales();

    SaleResponseDTO getSaleById(Long saleId);

    void deleteSale(Long saleId);
}
