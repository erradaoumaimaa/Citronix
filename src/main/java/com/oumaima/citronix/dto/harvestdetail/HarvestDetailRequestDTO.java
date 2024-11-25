package com.oumaima.citronix.dto.harvestdetail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HarvestDetailRequestDTO(
        @NotNull(message = "L'ID de l'arbre est obligatoire.")
        Long treeId,

        @NotNull(message = "La quantité est obligatoire.")
        @Positive(message = "La quantité doit être positive.")
        Double quantity
) {}
