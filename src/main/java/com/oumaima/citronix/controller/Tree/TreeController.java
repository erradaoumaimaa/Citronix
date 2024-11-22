package com.oumaima.citronix.controller.Tree;

import com.oumaima.citronix.dto.tree.*;
import com.oumaima.citronix.service.tree.TreeService;
import com.oumaima.citronix.utils.validation.OnCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trees")
public class TreeController {

    private final TreeService treeService;

    @Autowired
    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    // Save a new tree in the specified field
    @PostMapping("/{fieldId}/save")
    public ResponseEntity<TreeResponseDto> save(
            @PathVariable Long fieldId,
            @RequestBody @Validated(OnCreate.class) TreeRequestDTO treeRequestDTO) {
        TreeResponseDto createdTree = treeService.save(fieldId, treeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTree);
    }

    @PatchMapping("/{treeId}/update")
    public ResponseEntity<TreeResponseDto> update(
            @PathVariable Long treeId,
            @RequestBody TreeRequestDTO treeRequestDTO) {
        TreeResponseDto updatedTree = treeService.update(treeId, treeRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTree);
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<List<TreeResponseDto>> findAllTreesByField(@PathVariable Long fieldId) {
        List<TreeResponseDto> trees = treeService.findAllTreesByField(fieldId);
        return ResponseEntity.status(HttpStatus.OK).body(trees);
    }

    @GetMapping("/{treeId}/details")
    public ResponseEntity<TreeResponseDto> findById(@PathVariable Long treeId) {
        TreeResponseDto treeResponseDto = treeService.findById(treeId);
        return ResponseEntity.status(HttpStatus.OK).body(treeResponseDto);
    }

    @DeleteMapping("/{treeId}")
    public ResponseEntity<Void> delete(@PathVariable Long treeId) {
        treeService.delete(treeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
