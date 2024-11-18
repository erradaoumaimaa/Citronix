package com.oumaima.citronix.controller.farm;


import com.oumaima.citronix.dto.farm.FarmRequestDTO;
import com.oumaima.citronix.dto.farm.FarmResponseDTO;
import com.oumaima.citronix.service.farm.FarmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public ResponseEntity<FarmResponseDTO> create(@RequestBody @Valid FarmRequestDTO farmRequestDTO) {
        FarmResponseDTO createdFarm = farmService.create(farmRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFarm);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update/{farm_id}")
    public ResponseEntity<FarmResponseDTO> update(
            @PathVariable Long farm_id,
            @RequestBody @Valid FarmRequestDTO farmRequestDTO) {
        FarmResponseDTO updateFarm = farmService.update(farm_id, farmRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateFarm);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DeleteMapping("/remove/{farm_id}")
    public ResponseEntity<String> delete(@PathVariable Long farm_id) {
        farmService.delete(farm_id);
        return new ResponseEntity<>("The farm was deleted successfully.", HttpStatus.NO_CONTENT);
    }



}
