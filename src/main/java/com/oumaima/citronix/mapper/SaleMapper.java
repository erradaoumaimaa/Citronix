package com.oumaima.citronix.mapper;

import com.oumaima.citronix.dto.sale.SaleRequestDTO;
import com.oumaima.citronix.dto.sale.SaleResponseDTO;
import com.oumaima.citronix.entity.Sale;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SaleMapper {

    @Mapping(target = "harvestId", source = "harvest.id")
    SaleResponseDTO toResponseDTO(Sale sale);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "saleDate", ignore = true),
            @Mapping(target = "unitPrice", ignore = true),
            @Mapping(target = "harvest", ignore = true)
    })
    Sale createSaleFromSaleRequestDTO(SaleRequestDTO saleRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSaleFromDto(SaleRequestDTO dto, @MappingTarget Sale entity);
}
