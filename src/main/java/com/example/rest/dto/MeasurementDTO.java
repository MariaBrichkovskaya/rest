package com.example.rest.dto;

import com.example.rest.models.Sensor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementDTO {
    private Long id;
    private boolean raining;
    private Double value;
    private Sensor sensor;
}
