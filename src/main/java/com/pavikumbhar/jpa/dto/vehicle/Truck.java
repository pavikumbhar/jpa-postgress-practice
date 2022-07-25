package com.pavikumbhar.jpa.dto.vehicle;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Truck extends Vehicle {
    private int numOfWheels;

    @Override
    public String toString() {
        return "Truck{" +
                "name=" + getName() +
                ", getType=" + getType() +
                ", numOfWheels=" + numOfWheels +
                "} ";
    }
}
