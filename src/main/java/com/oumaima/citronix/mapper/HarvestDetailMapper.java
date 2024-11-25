package com.oumaima.citronix.mapper;

import com.oumaima.citronix.dto.harvestdetail.HarvestDetailRequestDTO;
import com.oumaima.citronix.dto.harvestdetail.HarvestDetailResponseDTO;
import com.oumaima.citronix.entity.HarvestDetail;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {

    @Mapping(target = "treeId", source = "tree.id")
    @Mapping(target = "harvestId", source = "harvest.id")
    HarvestDetailResponseDTO toResponseDTO(HarvestDetail harvestDetail);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "harvest", ignore = true),
            @Mapping(target = "tree", ignore = true)
    })
    HarvestDetail fromRequestDTO(HarvestDetailRequestDTO harvestDetailRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequestDTO(HarvestDetailRequestDTO dto, @MappingTarget HarvestDetail entity);
}
