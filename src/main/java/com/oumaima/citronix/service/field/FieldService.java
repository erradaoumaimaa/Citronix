package com.oumaima.citronix.service.field;

import com.oumaima.citronix.dto.field.FieldRequestDTO;
import com.oumaima.citronix.dto.field.FieldResponseDTO;
import com.oumaima.citronix.entity.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FieldService {

    FieldResponseDTO save(Long farmId, FieldRequestDTO fieldRequestDTO);

    FieldResponseDTO update(Long fieldId, FieldRequestDTO fieldRequestDTO);

    void delete(Long fieldId);

    Page<FieldResponseDTO> findAllByFarm(Long farmId, Pageable pageable);


}
