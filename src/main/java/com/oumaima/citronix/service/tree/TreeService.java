package com.oumaima.citronix.service.tree;

import com.oumaima.citronix.dto.tree.TreeRequestDTO;
import com.oumaima.citronix.dto.tree.TreeResponseDto;
import com.oumaima.citronix.entity.Tree;


import java.util.List;

public interface TreeService {

    TreeResponseDto save(Long fieldId, TreeRequestDTO treeRequestDTO);

    TreeResponseDto update(Long treeId, TreeRequestDTO treeRequestDTO);

    List<TreeResponseDto> findAllTreesByField(Long fieldId);

    TreeResponseDto findById(Long treeId);

    void delete(Long treeId);
}
