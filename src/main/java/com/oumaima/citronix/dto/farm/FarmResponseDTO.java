package com.oumaima.citronix.dto.farm;

import java.time.LocalDateTime;

public record FarmResponseDTO(
        Long id,
        String name,
        String location,
        double totalarea,
        LocalDateTime createdAt
){}
