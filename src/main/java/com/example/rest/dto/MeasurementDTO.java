package com.example.rest.dto;

import com.example.rest.models.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class MeasurementDTO {
    private Long id;
    private boolean raining;
    private Double value;
    private Sensor sensor;
}
