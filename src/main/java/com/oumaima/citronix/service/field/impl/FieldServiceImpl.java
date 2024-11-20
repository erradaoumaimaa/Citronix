package com.oumaima.citronix.service.field.impl;

import com.oumaima.citronix.dto.field.*;
import com.oumaima.citronix.entity.*;
import com.oumaima.citronix.exception.*;
import com.oumaima.citronix.mapper.FieldMapper;
import com.oumaima.citronix.repository.*;
import com.oumaima.citronix.service.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FarmRepository farmRepository;
    private final FieldMapper fieldMapper;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, FarmRepository farmRepository, FieldMapper fieldMapper) {
        this.fieldRepository = fieldRepository;
        this.farmRepository = farmRepository;
        this.fieldMapper = fieldMapper;
    }

    @Override
    public FieldResponseDTO save(Long farmId, FieldRequestDTO fieldRequestDTO) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new FarmNotFoundException("La ferme n'existe pas"));

        validateField(farm, fieldRequestDTO);

        Field fieldToSave = fieldMapper.createFieldFromFieldRequestDTO(fieldRequestDTO);

        fieldToSave.setFarm(farm);

        Field savedField = fieldRepository.save(fieldToSave);

        farm.getFields().add(savedField);
        farmRepository.save(farm);

        return fieldMapper.fieldToResponseDTO(savedField);
    }

    @Override
    public FieldResponseDTO update(Long fieldId, FieldRequestDTO fieldRequestDTO) {
        Field fieldToUpdate = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException("Le champ n'existe pas"));

        Farm farm = fieldToUpdate.getFarm();
        validateField(farm, fieldRequestDTO);

        fieldMapper.updateFieldFromDto(fieldRequestDTO, fieldToUpdate);

        Field updatedField = fieldRepository.save(fieldToUpdate);

        return fieldMapper.fieldToResponseDTO(updatedField);
    }

    @Override
    public void delete(Long fieldId) {
        Optional<Field> existe = fieldRepository.findById(fieldId);
        existe.ifPresentOrElse(
                field -> fieldRepository.delete(field),
                () -> { throw new FieldNotFoundException("Le champ n'existe pas"); }
        );
    }

    @Override
    public Page<FieldResponseDTO> findAllByFarm(Long farmId, Pageable pageable) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new FarmNotFoundException("La ferme n'existe pas"));

        Page<Field> fields = fieldRepository.findAllByFarmId(farmId, pageable);

        return fields.map(field -> fieldMapper.fieldToResponseDTO(field));
    }



    private void validateField(Farm farm, FieldRequestDTO fieldRequestDTO) {

        if (fieldRequestDTO.area() < 0.1) {
            throw new FieldAreaException("La superficie du champ doit être d'au moins 0.1 hectare.");
        }

        if (fieldRequestDTO.area() > farm.getTotalarea() * 0.5) {
            throw new FieldAreaException("Aucun champ ne peut dépasser 50% de la superficie totale de la ferme.");
        }

        double totalFieldArea = farm.getFields().stream()
                .mapToDouble(Field::getArea)
                .sum() + fieldRequestDTO.area();

        if (totalFieldArea > farm.getTotalarea()) {
            throw new FieldAreaException("La superficie totale des champs ne doit pas dépasser la superficie totale de la ferme.");
        }

        long totalFields = farm.getFields().size();
        if (totalFields >= 10) {
            throw new FieldCountException("Une ferme ne peut contenir plus de 10 champs.");
        }
    }


}
