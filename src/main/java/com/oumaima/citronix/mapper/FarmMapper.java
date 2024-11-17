package com.oumaima.citronix.mapper;


import com.oumaima.citronix.dto.farm.*;
import com.oumaima.citronix.entity.Farm;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public  interface FarmMapper {

  // Mapper Farm -> FarmResponseDto
    FarmResponseDto farmToFarmResponseDto(Farm farm);

    @Mapping(target = "id", ignore = true)
    // Mapper FarmRequestDTO -> Farm
    Farm farmRequestDtoToFarm(FarmRequestDTO farmRequestDto);

}
