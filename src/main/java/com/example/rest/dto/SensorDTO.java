package com.example.rest.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class SensorDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2,max=30,message="size is not correct")
    private String name;
}
