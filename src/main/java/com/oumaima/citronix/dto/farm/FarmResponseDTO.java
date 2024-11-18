package com.oumaima.citronix.dto.farm;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmResponseDTO {
    private Long id;
    private String name;
    private String location;
    private double totalarea;
    private LocalDateTime createdAt;

    public FarmResponseDTO(Long id, String name, String location, double totalarea) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.totalarea = totalarea;
    }
}
