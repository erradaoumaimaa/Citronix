package com.oumaima.citronix.mapper;

import com.oumaima.citronix.dto.field.FieldRequestDTO;
import com.oumaima.citronix.dto.field.FieldResponseDTO;
import com.oumaima.citronix.entity.Field;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    FieldResponseDTO fieldToResponseDTO(Field field);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "trees", ignore = true),
            @Mapping(target = "farm", ignore = true)
    })
    Field createFieldFromFieldRequestDTO(FieldRequestDTO fieldRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFieldFromDto(FieldRequestDTO dto, @MappingTarget Field entity);
}
