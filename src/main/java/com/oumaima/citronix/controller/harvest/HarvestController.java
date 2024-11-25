package com.oumaima.citronix.controller.harvest;

import com.oumaima.citronix.dto.field.FieldRequestDTO;
import com.oumaima.citronix.dto.field.FieldResponseDTO;
import com.oumaima.citronix.dto.harvest.*;
import com.oumaima.citronix.service.harvest.HarvestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/harvests")
public class HarvestController {
    private final HarvestService harvestService;

    public HarvestController(HarvestService harvestService) {
        this.harvestService = harvestService;
    }

    @PostMapping("/{fieldId}/save")
    public ResponseEntity<HarvestResponseDTO> save(
            @PathVariable Long fieldId,
            @RequestBody @Valid HarvestRequestDTO harvestRequestDTO) {

        HarvestResponseDTO createdHarvest = harvestService.createHarvest(fieldId, harvestRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHarvest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        harvestService.deleteHarvest(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping
    public ResponseEntity<List<HarvestResponseDTO>> getAllHarvests() {
        List<HarvestResponseDTO> harvests = harvestService.getAllHarvests();
        return ResponseEntity.status(HttpStatus.OK).body(harvests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> getHarvestById(@PathVariable Long id) {
        HarvestResponseDTO harvest = harvestService.getHarvestById(id);
        return ResponseEntity.status(HttpStatus.OK).body(harvest);
    }

    @PatchMapping("/{harvestId}/update")
    public ResponseEntity<HarvestResponseDTO> update(
            @PathVariable Long harvestId,
            @RequestBody @Valid HarvestRequestDTO harvestRequestDTO) {
        HarvestResponseDTO updatedHarvest = harvestService.updateHarvest(harvestId, harvestRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedHarvest);
    }
}
