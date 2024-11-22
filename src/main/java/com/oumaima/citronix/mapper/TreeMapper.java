package com.oumaima.citronix.mapper;

import com.oumaima.citronix.dto.tree.*;
import com.oumaima.citronix.entity.Tree;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {FieldMapper.class})
public interface TreeMapper {


    TreeResponseDto treeToResponseDTO(Tree tree);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "field", ignore = true)
    })
    Tree createTreeFromTreeRequestDTO(TreeRequestDTO treeRequestDTO);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTreeFromDto(TreeRequestDTO dto, @MappingTarget Tree entity);
}
