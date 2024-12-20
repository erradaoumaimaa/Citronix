package com.oumaima.citronix.service.harvest.impl;

import com.oumaima.citronix.dto.harvest.*;
import com.oumaima.citronix.entity.*;
import com.oumaima.citronix.entity.enums.Season;
import com.oumaima.citronix.exception.*;
import com.oumaima.citronix.mapper.HarvestMapper;
import com.oumaima.citronix.repository.*;
import com.oumaima.citronix.service.harvest.HarvestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final HarvestMapper harvestMapper;
    private final FieldRepository fieldRepository;
    private final TreeRepository treeRepository;

    public HarvestServiceImpl(HarvestRepository harvestRepository, HarvestMapper harvestMapper, FieldRepository fieldRepository, TreeRepository treeRepository) {
        this.harvestRepository = harvestRepository;
        this.harvestMapper = harvestMapper;
        this.fieldRepository = fieldRepository;
        this.treeRepository = treeRepository;
    }

    @Override
    public HarvestResponseDTO createHarvest(Long fieldId, HarvestRequestDTO harvestRequestDTO) {

        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException("Le champ spécifié est introuvable."));

        Season season = determineSeason(harvestRequestDTO.harvestDate());
        int year = harvestRequestDTO.harvestDate().getYear();

        if (harvestRepository.existsByFieldAndSeasonAndHarvestDateYear(field, season, year)) {
            throw new IllegalArgumentException("Une récolte existe déjà pour ce champ durant la saison " + season + " de l'année " + year + ".");
        }

        Harvest harvest = harvestMapper.createHarvestFromHarvestRequestDTO(harvestRequestDTO);
        harvest.setSeason(season);
        harvest.setField(field);

        List<HarvestDetail> harvestDetails = calculateProductivityDetails(harvest);
        harvest.setHarvestDetails(harvestDetails);

        double totalQuantity = calculateTotalProductivity(harvestDetails);
        harvest.setTotalQuantity(totalQuantity);

        harvest = harvestRepository.save(harvest);

        return harvestMapper.toResponseDTO(harvest);
    }
    @Override
    public HarvestResponseDTO updateHarvest(Long id, HarvestRequestDTO harvestRequestDTO) {
        Harvest harvestToUpdate = harvestRepository.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("La récolte spécifiée est introuvable."));

        Field field = fieldRepository.findById(harvestRequestDTO.fieldId())
                .orElseThrow(() -> new FieldNotFoundException("Le champ spécifié est introuvable."));

        harvestMapper.updateHarvestFromDto(harvestRequestDTO, harvestToUpdate);
        harvestToUpdate.setField(field);

        Harvest updatedHarvest = harvestRepository.save(harvestToUpdate);

        return harvestMapper.toResponseDTO(updatedHarvest);
    }


    private Season determineSeason(LocalDate harvestDate) {
        int month = harvestDate.getMonthValue();
        if (month == 12 || month <= 2) {
            return Season.WINTER;
        } else if (month <= 5) {
            return Season.SPRING;
        } else if (month <= 8) {
            return Season.SUMMER;
        } else {
            return Season.AUTUMN;
        }
    }

    private double calculateTotalProductivity(List<HarvestDetail> harvestDetails) {
        if (harvestDetails == null || harvestDetails.isEmpty()) {
            return 0.0;
        }
        return harvestDetails.stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();
    }

    private List<HarvestDetail> calculateProductivityDetails(Harvest harvest) {
        List<Tree> trees = harvest.getField().getTrees();
        return trees.stream()
                .map(t -> HarvestDetail.builder()
                        .quantity(t.getAnnualProductivity())
                        .tree(t)
                        .harvest(harvest)
                        .build())
                .toList();
    }

    @Override
    public void deleteHarvest(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("La récolte spécifiée est introuvable."));
        harvestRepository.delete(harvest);
    }

    @Override
    public List<HarvestResponseDTO> getAllHarvests() {
        List<Harvest> harvests = harvestRepository.findAll();
        return harvests.stream()
                .map(harvestMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HarvestResponseDTO getHarvestById(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("La récolte spécifiée est introuvable."));
        return harvestMapper.toResponseDTO(harvest);
    }

    @Override
    public List<HarvestResponseDTO> getHarvestsBySeason(Season season) {
        List<Harvest> harvests = harvestRepository.findBySeason(season);
        return harvests.stream()
                .map(harvestMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    public List<Harvest> getAllHarvestByYearAndSeason(int year, Season season){
        return harvestRepository.findAll()
                .stream()
                .filter(h->h.getSeason().equals(season)
                && h.getHarvestDate().getYear() == year)
                .toList();

    }






}