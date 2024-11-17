package com.oumaima.citronix.dto.farm;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmResponseDto {
    private UUID id;
    private String name;
    private String location;
    private double area;
    private LocalDateTime createdAt;
}
