package com.example.rest.services;

import com.example.rest.models.Sensor;
import com.example.rest.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
