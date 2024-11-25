package com.oumaima.citronix.controller.sale;

import com.oumaima.citronix.dto.sale.SaleRequestDTO;
import com.oumaima.citronix.dto.sale.SaleResponseDTO;
import com.oumaima.citronix.service.sale.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/{harvestId}")
    public ResponseEntity<SaleResponseDTO> createSale(
            @PathVariable Long harvestId,
            @RequestBody SaleRequestDTO saleRequestDTO) {
        SaleResponseDTO saleResponseDTO = saleService.createSale(harvestId, saleRequestDTO);
        return new ResponseEntity<>(saleResponseDTO, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        List<SaleResponseDTO> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long saleId) {
        SaleResponseDTO saleResponseDTO = saleService.getSaleById(saleId);
        return new ResponseEntity<>(saleResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long saleId) {
        saleService.deleteSale(saleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
