package com.example.rest.util;

public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(String message) {
        super(message);
    }
}
