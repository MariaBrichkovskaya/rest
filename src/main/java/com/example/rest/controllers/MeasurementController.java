package com.example.rest.controllers;

import com.example.rest.dto.MeasurementDTO;
import com.example.rest.models.Measurement;
import com.example.rest.services.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@Transactional(readOnly = true)
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    @Autowired

    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/add")
    @Transactional
    public ResponseEntity<HttpStatus> add(@RequestBody MeasurementDTO measurementDTO){
        Measurement measurement=convertToEntity(measurementDTO);
        measurement.setSensor(measurementService.findByName(measurementDTO.getSensor().getName()));
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping
    public List<Measurement> getAll(){
        return measurementService.findAll();
    }
    @GetMapping("/rainyDaysCount")
    public Integer rainyDaysCount(){
        return Math.toIntExact(measurementService.findRaining().stream().count());
    }
    private Measurement convertToEntity (MeasurementDTO measurementDTO)
    {

        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);

        return measurement;
    }
}
