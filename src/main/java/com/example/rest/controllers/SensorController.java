package com.example.rest.controllers;

import com.example.rest.dto.SensorDTO;
import com.example.rest.models.Sensor;
import com.example.rest.services.SensorService;
import com.example.rest.util.ErrorResponse;
import com.example.rest.util.SensorNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){

        if (bindingResult.hasErrors())
        {
            FieldError error=bindingResult.getFieldError();
            throw new SensorNotCreatedException(error.getDefaultMessage());
        }
        if (!(sensorService.findByName(sensorDTO.getName())==null)){
            throw new SensorNotCreatedException("Name should be unique");
        }
        sensorService.save(modelMapper.map(sensorDTO, Sensor.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handle(SensorNotCreatedException exception){
        ErrorResponse response=new ErrorResponse(exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
