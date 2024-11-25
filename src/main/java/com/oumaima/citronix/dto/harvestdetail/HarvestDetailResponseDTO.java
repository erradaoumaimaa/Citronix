package com.oumaima.citronix.dto.harvestdetail;

public record HarvestDetailResponseDTO(
        Long id,
        Long treeId,
        Long harvestId,
        Double quantity
) {}
