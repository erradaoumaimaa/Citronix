package com.oumaima.citronix.service.field.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.oumaima.citronix.dto.field.FieldRequestDTO;
import com.oumaima.citronix.entity.Farm;
import com.oumaima.citronix.entity.Field;
import com.oumaima.citronix.exception.FieldAreaException;
import com.oumaima.citronix.exception.FieldCountException;
import com.oumaima.citronix.mapper.FieldMapper;
import com.oumaima.citronix.repository.FarmRepository;
import com.oumaima.citronix.repository.FieldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;
    @Mock
    private FarmRepository farmRepository;
    @Mock
    private FieldMapper fieldMapper;

    private FieldServiceImpl fieldService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fieldService = new FieldServiceImpl(fieldRepository, farmRepository, fieldMapper);
    }

    @Test
    void testValidateField_areaLessThanMinimum() {
        Long farmId = 1L;
        Farm farm = new Farm();
        farm.setId(farmId);
        farm.setTotalarea(100.0);

        farm.setFields(new ArrayList<>());

        FieldRequestDTO fieldRequestDTO = new FieldRequestDTO(0.05);

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));

        assertThrows(FieldAreaException.class, () -> {
            fieldService.save(farmId, fieldRequestDTO);
        });
    }

    @Test
    void testValidateField_areaGreaterThan50PercentOfFarm() {
        Long farmId = 1L;
        Farm farm = new Farm();
        farm.setId(farmId);
        farm.setTotalarea(100.0);

        farm.setFields(new ArrayList<>());

        FieldRequestDTO fieldRequestDTO = new FieldRequestDTO(60.0);

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));

        assertThrows(FieldAreaException.class, () -> {
            fieldService.save(farmId, fieldRequestDTO);
        });
    }

    @Test
    void testValidateField_totalFieldAreaExceedsFarmArea() {
        Long farmId = 1L;
        Farm farm = new Farm();
        farm.setId(farmId);
        farm.setTotalarea(100.0);

        farm.setFields(new ArrayList<>());

        Field existingField = new Field();
        existingField.setArea(95.0);

        farm.getFields().add(existingField);

        FieldRequestDTO fieldRequestDTO = new FieldRequestDTO(10.0);

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));

        assertThrows(FieldAreaException.class, () -> {
            fieldService.save(farmId, fieldRequestDTO);
        });
    }

    @Test
    void testValidateField_tooManyFieldsInFarm() {
        Long farmId = 1L;
        Farm farm = new Farm();
        farm.setId(farmId);
        farm.setTotalarea(100.0);

        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Field field = new Field();
            field.setArea(10.0);
            fields.add(field);
        }
        farm.setFields(fields);

        FieldRequestDTO fieldRequestDTO = new FieldRequestDTO(10.0);

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));

        assertThrows(FieldCountException.class, () -> {
            fieldService.save(farmId, fieldRequestDTO);
        });
    }
}
