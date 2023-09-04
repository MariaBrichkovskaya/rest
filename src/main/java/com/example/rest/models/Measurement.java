package com.example.rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
@Getter
@Setter
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "raining")
    private boolean raining;
    @Column(name = "value")
    private Double value;
    @Column(name = "date")
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    private Sensor sensor;
}
