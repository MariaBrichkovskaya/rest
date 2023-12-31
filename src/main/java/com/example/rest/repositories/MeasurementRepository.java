package com.example.rest.repositories;

import com.example.rest.models.Measurement;
import com.example.rest.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Long> {
    List<Measurement> findByRainingTrue();
    Measurement findBySensor(Sensor sensor);
}
