package com.oumaima.citronix.controller.harvestDetail;

import com.oumaima.citronix.dto.harvestdetail.HarvestDetailResponseDTO;
import com.oumaima.citronix.service.harvestDetail.HarvestDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/harvest-details")
public class HarvestDetailController {

    private final HarvestDetailService harvestDetailService;

    public HarvestDetailController(HarvestDetailService harvestDetailService) {
        this.harvestDetailService = harvestDetailService;
    }

    @GetMapping("/harvest/{harvestId}")
    public ResponseEntity<List<HarvestDetailResponseDTO>> getHarvestDetailsByHarvestId(@PathVariable Long harvestId) {
        List<HarvestDetailResponseDTO> harvestDetails = harvestDetailService.getHarvestDetailsByHarvestId(harvestId);
        return ResponseEntity.ok(harvestDetails);
    }
}