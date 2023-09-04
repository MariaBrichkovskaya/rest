package com.example.rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "measurements")
@Getter
@Setter
public class Measurement {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "raining")
    private boolean raining;
    @Column(name = "value")
    private Double value;
    @ManyToOne
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    private Sensor sensor;
}
