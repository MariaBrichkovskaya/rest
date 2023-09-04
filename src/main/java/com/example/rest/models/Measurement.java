package com.example.rest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
@Getter
@Setter
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "raining")
    private boolean raining;
    @Column(name = "value")
    private Double value;
    @Column(name = "date")
    private LocalDateTime dateTime;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    //@Cascade({ org.hibernate.annotations.CascadeType.ALL })
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    private Sensor sensor;
}
