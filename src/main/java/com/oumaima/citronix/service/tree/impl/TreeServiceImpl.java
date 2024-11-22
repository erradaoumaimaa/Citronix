package com.oumaima.citronix.service.tree.impl;

import com.oumaima.citronix.dto.tree.*;
import com.oumaima.citronix.entity.*;
import com.oumaima.citronix.exception.*;
import com.oumaima.citronix.mapper.TreeMapper;
import com.oumaima.citronix.repository.*;
import com.oumaima.citronix.service.tree.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    private final FieldRepository fieldRepository;
    private final TreeMapper treeMapper;
    private final TreeRepository treeRepository;
    @Autowired
    public TreeServiceImpl(FieldRepository fieldRepository, TreeMapper treeMapper, TreeRepository treeRepository) {
        this.fieldRepository = fieldRepository;
        this.treeMapper = treeMapper;
        this.treeRepository = treeRepository;
    }
    @Override
    public TreeResponseDto save(Long fieldId, TreeRequestDTO treeRequestDTO) {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException("Field not found"));

        long currentTreeCount = treeRepository.countByFieldId(fieldId);
        double maxTrees = field.getArea() * 100;
        if (currentTreeCount >= maxTrees) {
            throw new FieldAreaException("Field has reached the maximum tree density.");
        }
        Tree treeToSave = treeMapper.createTreeFromTreeRequestDTO(treeRequestDTO);

        treeToSave.setField(field);

        treeToSave.validateTreeConstraints();

        Tree savedTree = treeRepository.save(treeToSave);

        field.getTrees().add(savedTree);
        fieldRepository.save(field);

        return treeMapper.treeToResponseDTO(savedTree);
    }



    @Override
    public TreeResponseDto update(Long treeId, TreeRequestDTO treeRequestDTO) {

        Tree treeToUpdate = treeRepository.findById(treeId)
                .orElseThrow(() -> new TreeNotFoundException("Tree not found"));

        Field field = treeToUpdate.getField();

        treeMapper.updateTreeFromDto(treeRequestDTO, treeToUpdate);

        treeToUpdate.validateTreeConstraints();

        Tree updatedTree = treeRepository.save(treeToUpdate);

        return treeMapper.treeToResponseDTO(updatedTree);
    }

    @Override
    public List<TreeResponseDto> findAllTreesByField(Long fieldId) {
        fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException("Field not found"));

        return treeRepository.findAllByFieldId(fieldId)
                .stream()
                .map(treeMapper::treeToResponseDTO)
                .toList();
    }

    @Override
    public TreeResponseDto findById(Long treeId) {
        Tree tree = treeRepository.findById(treeId)
                .orElseThrow(() -> new TreeNotFoundException("Tree not found"));
        return treeMapper.treeToResponseDTO(tree);
    }


    @Override
    public void delete(Long treeId) {
            treeRepository.deleteById(treeId);
    }
}
