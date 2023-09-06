package com.example.rest.dto;

import com.example.rest.models.Sensor;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class MeasurementDTO {
    private boolean raining;

    @NotNull
    @Range(min=-50, max=50)
    private Double value;
    @NotNull
    private SensorDTO sensor;
}
