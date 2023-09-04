package com.example.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDTO {
    //@NotEmpty(message = "Name should not be empty")
    //@Size(min=2,max=30,message="")
    private String name;
}
