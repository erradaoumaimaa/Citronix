package com.oumaima.citronix.controller.farm;


import com.oumaima.citronix.dto.farm.FarmRequestDTO;
import com.oumaima.citronix.dto.farm.FarmResponseDTO;
import com.oumaima.citronix.service.farm.FarmService;
import com.oumaima.citronix.utils.validation.OnCreate;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }
    @PostMapping("/create")
    public ResponseEntity<FarmResponseDTO> create(@RequestBody @Validated(OnCreate.class) FarmRequestDTO farmRequestDTO) {
        FarmResponseDTO createdFarm = farmService.create(farmRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFarm);
    }
@PatchMapping("/update/{farm_id}")
    public ResponseEntity<FarmResponseDTO> update(
            @PathVariable Long farm_id,
            @RequestBody @Valid FarmRequestDTO farmRequestDTO) {
        FarmResponseDTO updateFarm = farmService.update(farm_id, farmRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateFarm);
    }

    @DeleteMapping("/remove/{farm_id}")
    public ResponseEntity<String> delete(@PathVariable Long farm_id) {
        farmService.delete(farm_id);
        return new ResponseEntity<>("The farm was deleted successfully.", HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<Page<FarmResponseDTO>> getAll(Pageable pageable) {
        Page<FarmResponseDTO> farms = farmService.findAll(pageable);
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/{farm_id}")
    public ResponseEntity<FarmResponseDTO> getById(@PathVariable Long farm_id) {
        FarmResponseDTO farm = farmService.findById(farm_id);
        return ResponseEntity.ok(farm);
    }

}
