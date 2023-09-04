package com.example.rest.services;

import com.example.rest.models.Measurement;
import com.example.rest.models.Sensor;
import com.example.rest.repositories.MeasurementRepository;
import com.example.rest.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService  {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }
    @Transactional
    public void save(Measurement measurement){
        measurement.setDateTime(LocalDateTime.now());
        measurementRepository.save(measurement);
    }
    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }
    public List<Measurement> findRaining(){
        return measurementRepository.findByRainingTrue();
    }
    public Sensor findByName(String name){
        return sensorRepository.findByName(name);
    }
}
