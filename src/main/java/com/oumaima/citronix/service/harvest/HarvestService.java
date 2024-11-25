package com.oumaima.citronix.service.harvest;

import com.oumaima.citronix.dto.harvest.HarvestRequestDTO;
import com.oumaima.citronix.dto.harvest.HarvestResponseDTO;
import com.oumaima.citronix.entity.enums.Season;

import java.util.List;

public interface HarvestService {
    HarvestResponseDTO createHarvest(Long fieldId, HarvestRequestDTO harvestRequestDTO);
    HarvestResponseDTO updateHarvest(Long id, HarvestRequestDTO harvestRequestDTO);
    void deleteHarvest(Long id);
    List<HarvestResponseDTO> getAllHarvests();
    HarvestResponseDTO getHarvestById(Long id);
    List<HarvestResponseDTO> getHarvestsBySeason(Season season);
}
