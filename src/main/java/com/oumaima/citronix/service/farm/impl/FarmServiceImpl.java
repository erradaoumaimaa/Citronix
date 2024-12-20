package com.oumaima.citronix.service.farm.impl;

import com.oumaima.citronix.dto.farm.FarmRequestDTO;
import com.oumaima.citronix.dto.farm.FarmResponseDTO;
import com.oumaima.citronix.entity.Farm;
import com.oumaima.citronix.exception.EntityNotFoundException;
import com.oumaima.citronix.exception.FarmNotFoundException;
import com.oumaima.citronix.mapper.FarmMapper;
import com.oumaima.citronix.repository.FarmRepository;
import com.oumaima.citronix.service.farm.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    public FarmServiceImpl(FarmRepository farmRepository, FarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;
    }
    @Override
    public FarmResponseDTO save(FarmRequestDTO farmRequestDTO) {
        Optional<Farm> existe = farmRepository.findByName(farmRequestDTO.name());
        if (existe.isPresent()) {
            throw new RuntimeException("Farm already exists");
        }
        Farm farmToSave = farmMapper.createFarmFromFarmRequestDto(farmRequestDTO);
        Farm saveFarm= farmRepository.save(farmToSave);

        return farmMapper.farmToFarmResponseDto(saveFarm);
    }

    @Override
    public FarmResponseDTO update(Long id, FarmRequestDTO farmRequestDTO) {
        Farm farmToUpdate = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not exists"));

        farmMapper.updateFarmFromDto(farmRequestDTO, farmToUpdate);

        Farm updatedFarm = farmRepository.save(farmToUpdate);

        return farmMapper.farmToFarmResponseDto(updatedFarm);
    }


    @Override
    public void delete(Long id) {
        Optional<Farm> existe = farmRepository.findById(id);
        existe.ifPresentOrElse(
                farm -> farmRepository.delete(farm),
                () -> { throw new RuntimeException("Farm not found"); }
        );
    }

    @Override
    public FarmResponseDTO findById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm with id " + id + " not found"));
        return farmMapper.farmToFarmResponseDto(farm);
    }

    @Override
    public Page<FarmResponseDTO> findAll(Pageable pageable) {
        Page<Farm> farmPage = farmRepository.findAll(pageable);

        return farmPage.map(farm -> farmMapper.farmToFarmResponseDto(farm));
    }


}
