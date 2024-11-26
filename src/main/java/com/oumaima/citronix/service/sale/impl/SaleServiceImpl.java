package com.oumaima.citronix.service.sale.impl;

import com.oumaima.citronix.dto.sale.RevenueDTO;
import com.oumaima.citronix.dto.sale.SaleRequestDTO;
import com.oumaima.citronix.dto.sale.SaleResponseDTO;
import com.oumaima.citronix.entity.Harvest;
import com.oumaima.citronix.entity.Sale;
import com.oumaima.citronix.exception.HarvestNotFoundException;
import com.oumaima.citronix.exception.InsufficientQuantityException;
import com.oumaima.citronix.exception.SaleNotFoundException;
import com.oumaima.citronix.mapper.SaleMapper;
import com.oumaima.citronix.repository.HarvestRepository;
import com.oumaima.citronix.repository.SaleRepository;
import com.oumaima.citronix.service.sale.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;
    private final SaleMapper saleMapper;

    public SaleServiceImpl(SaleRepository saleRepository,
                           HarvestRepository harvestRepository,
                           SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.harvestRepository = harvestRepository;
        this.saleMapper = saleMapper;
    }

    @Override
    public SaleResponseDTO createSale(Long harvestId, SaleRequestDTO saleRequestDTO) {
        Harvest harvest = harvestRepository.findById(harvestId)
                .orElseThrow(() -> new HarvestNotFoundException("Harvest not found"));

        if (saleRequestDTO.quantity() > harvest.getTotalQuantity()) {
            throw new IllegalArgumentException("Insufficient quantity in harvest");
        }

        harvest.setTotalQuantity(harvest.getTotalQuantity() - saleRequestDTO.quantity());
        harvestRepository.save(harvest);

        Sale sale = Sale.builder()
                .client(saleRequestDTO.client())
                .quantity(saleRequestDTO.quantity())
                .unitPrice(saleRequestDTO.unitPrice())
                .harvest(harvest)
                .build();

        double revenue = saleRequestDTO.quantity() * saleRequestDTO.unitPrice();

        saleRepository.save(sale);

        return new SaleResponseDTO(
                sale.getId(),
                sale.getClient(),
                sale.getSaleDate(),
                sale.getQuantity(),
                sale.getUnitPrice(),
                sale.getHarvest().getId(),
                revenue
        );
    }

    public double calculateRevenue(Sale sale) {
        return sale.getUnitPrice() * sale.getQuantity();
    }

    public double calculateTotalRevenue(List<Sale> sales) {
        if(sales == null || sales.isEmpty()) return 0;
        return sales.stream().mapToDouble(this::calculateRevenue).sum();
    }

    public List<Sale> getAllSaleEntities() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream().map(s -> s.withRevenue(calculateRevenue(s))).toList();
    }

    @Override
    public RevenueDTO getAllSales() {
        List<Sale> sales = getAllSaleEntities();
        return new RevenueDTO(calculateTotalRevenue(sales), sales.stream().map(saleMapper::toResponseDTO).toList());
    }

    @Override
    public SaleResponseDTO getSaleById(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Vente introuvable."));
        return saleMapper.toResponseDTO(sale.withRevenue(calculateRevenue(sale)));
    }

    @Override
    public void deleteSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Vente introuvable."));
        saleRepository.delete(sale);
    }





}
