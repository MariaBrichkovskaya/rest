package com.example.rest.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "sensors")
@Getter
@Setter
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true)
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2,max=30,message="size is not correct")
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "sensor",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Measurement> measurements;

}
