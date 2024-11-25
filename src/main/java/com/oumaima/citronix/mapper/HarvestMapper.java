package com.oumaima.citronix.mapper;

import com.oumaima.citronix.dto.harvest.HarvestRequestDTO;
import com.oumaima.citronix.dto.harvest.HarvestResponseDTO;
import com.oumaima.citronix.entity.Harvest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    HarvestResponseDTO toResponseDTO(Harvest harvest);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "season", ignore = true),
            @Mapping(target = "field", ignore = true),
            @Mapping(target = "totalQuantity", ignore = true)

    })
    Harvest createHarvestFromHarvestRequestDTO(HarvestRequestDTO harvestRequestDTO);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHarvestFromDto(HarvestRequestDTO dto, @MappingTarget Harvest entity);
}
