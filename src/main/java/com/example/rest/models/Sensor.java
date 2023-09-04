package com.example.rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sensors")
@Getter
@Setter
public class Sensor {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name",unique = true)
    //@NotEmpty(message = "Name should not be empty")
    private String name;
    @OneToMany(mappedBy = "sensor",fetch = FetchType.LAZY)
    private List<Measurement> measurements;

}
