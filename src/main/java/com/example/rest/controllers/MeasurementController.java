package com.example.rest.controllers;

import com.example.rest.dto.MeasurementDTO;
import com.example.rest.models.Measurement;
import com.example.rest.services.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    @Autowired

    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody MeasurementDTO measurementDTO){
        measurementService.save(modelMapper.map(measurementDTO, Measurement.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping
    public List<Measurement> getAll(){
        return measurementService.findAll();
    }
    @GetMapping("/rainyDaysCount")
    public List<Measurement> rainyDaysCount(){
        return measurementService.findRaining();
    }
}
