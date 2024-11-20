package com.oumaima.citronix.service.farm;

import com.oumaima.citronix.dto.farm.FarmRequestDTO;
import com.oumaima.citronix.dto.farm.FarmResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FarmService {

    FarmResponseDTO save(FarmRequestDTO farmRequestDTO);

    FarmResponseDTO update(Long id, FarmRequestDTO farmRequestDTO);

    void delete(Long id);

    FarmResponseDTO findById(Long id);

    Page<FarmResponseDTO> findAll(Pageable pageable);
}
