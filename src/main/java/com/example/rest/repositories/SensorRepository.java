package com.example.rest.repositories;

import com.example.rest.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {
    Sensor findByName(String name);
}
