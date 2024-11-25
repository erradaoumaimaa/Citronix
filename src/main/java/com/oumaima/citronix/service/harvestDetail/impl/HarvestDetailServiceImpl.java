package com.oumaima.citronix.service.harvestdetail.impl;

import com.oumaima.citronix.dto.harvestdetail.HarvestDetailResponseDTO;
import com.oumaima.citronix.entity.Harvest;
import com.oumaima.citronix.exception.HarvestNotFoundException;
import com.oumaima.citronix.mapper.HarvestDetailMapper;
import com.oumaima.citronix.repository.HarvestDetailRepository;
import com.oumaima.citronix.repository.HarvestRepository;
import com.oumaima.citronix.service.harvestDetail.HarvestDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestDetailServiceImpl implements HarvestDetailService {

    private final HarvestDetailRepository harvestDetailRepository;
    private final HarvestRepository harvestRepository;
    private final HarvestDetailMapper harvestDetailMapper;

    public HarvestDetailServiceImpl(HarvestDetailRepository harvestDetailRepository,
                                    HarvestRepository harvestRepository,
                                    HarvestDetailMapper harvestDetailMapper) {
        this.harvestDetailRepository = harvestDetailRepository;
        this.harvestRepository = harvestRepository;
        this.harvestDetailMapper = harvestDetailMapper;
    }

    @Override
    public List<HarvestDetailResponseDTO> getHarvestDetailsByHarvestId(Long harvestId) {
        Harvest harvest = harvestRepository.findById(harvestId)
                .orElseThrow(() -> new HarvestNotFoundException("La récolte spécifiée est introuvable."));

        return harvest.getHarvestDetails().stream()
                .map(harvestDetailMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
