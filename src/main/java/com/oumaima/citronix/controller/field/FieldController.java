package com.oumaima.citronix.controller.field;


import com.oumaima.citronix.dto.field.*;
import com.oumaima.citronix.entity.Field;
import com.oumaima.citronix.service.field.*;
import com.oumaima.citronix.utils.validation.OnCreate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fields")
public class FieldController {

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    // Corrected the @RequestBody and @PathVariable usage
    @PostMapping("/{farmId}/save")
    public ResponseEntity<FieldResponseDTO> save(
            @PathVariable Long farmId,
            @RequestBody @Validated(OnCreate.class) FieldRequestDTO fieldRequestDTO) {

        FieldResponseDTO createdField = fieldService.save(farmId, fieldRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdField);
    }

    @PatchMapping("/{fieldId}/update")
    public ResponseEntity<FieldResponseDTO> update(
            @PathVariable Long fieldId,
            @RequestBody @Valid FieldRequestDTO fieldRequestDTO) {
        FieldResponseDTO updatedField = fieldService.update(fieldId, fieldRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedField);
    }

    @DeleteMapping("/{fieldId}/remove")
    public ResponseEntity<String> delete(@PathVariable Long fieldId) {
        fieldService.delete(fieldId);
        return new ResponseEntity<>("Field deleted successfully.", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<Page<FieldResponseDTO>> getAll(
            @PathVariable Long farmId,
            Pageable pageable) {
        Page<FieldResponseDTO> fields = fieldService.findAllByFarm(farmId, pageable);
        return ResponseEntity.ok(fields);
    }

}
