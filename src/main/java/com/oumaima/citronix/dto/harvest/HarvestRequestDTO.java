package com.oumaima.citronix.dto.harvest;

import com.oumaima.citronix.utils.validation.OnCreate;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record HarvestRequestDTO(
        @NotNull(message = "La date de récolte ne peut pas être nulle." ,groups = OnCreate.class)
        LocalDate harvestDate,
         @NotNull(message = "L'identifiant du champ ne peut pas être nul.")
         Long fieldId
) {}
