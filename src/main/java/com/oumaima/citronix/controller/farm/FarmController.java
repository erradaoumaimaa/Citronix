package com.oumaima.citronix.controller.farm;


import com.oumaima.citronix.dto.farm.FarmRequestDTO;
import com.oumaima.citronix.dto.farm.FarmResponseDTO;
import com.oumaima.citronix.service.farm.FarmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }


    @PostMapping("/create")
    public ResponseEntity<FarmResponseDTO> create(@RequestBody @Valid FarmRequestDTO farmRequestDTO) {
        FarmResponseDTO createdFarm = farmService.create(farmRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFarm);
    }
}
