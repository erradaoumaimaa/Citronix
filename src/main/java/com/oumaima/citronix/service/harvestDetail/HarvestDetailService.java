package com.oumaima.citronix.service.harvestDetail;

import com.oumaima.citronix.dto.harvestdetail.HarvestDetailResponseDTO;

import java.util.List;

public interface HarvestDetailService {
    List<HarvestDetailResponseDTO> getHarvestDetailsByHarvestId(Long harvestId);
}
