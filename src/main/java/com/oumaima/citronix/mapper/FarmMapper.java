package com.oumaima.citronix.mapper;

import com.oumaima.citronix.dto.farm.*;
import com.oumaima.citronix.entity.Farm;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FarmMapper {


  FarmResponseDTO farmToFarmResponseDto(Farm farm);

  @Mappings({
          @Mapping(target = "id", ignore = true)
  })
  Farm createFarmFromFarmRequestDto(FarmRequestDTO farmRequestDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateFarmFromDto(FarmRequestDTO dto, @MappingTarget Farm entity);
}

