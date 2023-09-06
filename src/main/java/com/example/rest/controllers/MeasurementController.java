package com.example.rest.controllers;

import com.example.rest.dto.MeasurementDTO;
import com.example.rest.models.Measurement;
import com.example.rest.models.Sensor;
import com.example.rest.services.MeasurementService;

import com.example.rest.util.ErrorResponse;
import com.example.rest.util.MeasurementNotCreatedException;
import com.example.rest.util.SensorNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
        {
            StringBuilder msg=new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for (FieldError error:errors){
                msg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementNotCreatedException(msg.toString());
        }
        Measurement measurement = convertToEntity(measurementDTO);
        Sensor sensor = measurementService.findByName(measurementDTO.getSensor().getName());
        if(sensor==null) throw new SensorNotFoundException("There is no such Sensor");
        measurement.setSensor(sensor);
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);

    }
    @GetMapping
    public List<MeasurementDTO> getAll(){

        List<Measurement> measurements = measurementService.findAll();
        return measurements.stream()
                .map(measurement -> new ModelMapper().map(measurement, MeasurementDTO.class))
                .collect(Collectors.toList());
        //return measurementService.findAll();
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
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handle(MeasurementNotCreatedException exception){
        ErrorResponse response=new ErrorResponse(exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handle(SensorNotFoundException exception){
        ErrorResponse response=new ErrorResponse(exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
