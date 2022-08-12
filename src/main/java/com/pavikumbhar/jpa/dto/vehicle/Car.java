package com.pavikumbhar.jpa.dto.vehicle;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Car extends Vehicle {
    private boolean sunRoof;


    @Override
    public String toString() {
        return "Car{" +
                "name=" + getName() +
                ", getType=" + getType() +
                ", sunRoof=" + sunRoof +
                "} ";
    }
}
