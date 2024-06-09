package com.sachdeva.ParkingLot.model;

import org.springframework.stereotype.Component;

public record Car(String regNo, String color, String type) {
    public Car(String regNo, String color) {
        this(regNo, color, "CAR");
    }
}