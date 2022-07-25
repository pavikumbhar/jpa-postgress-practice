package com.pavikumbhar.jpa.dto.vehicle;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Plane extends Vehicle {
    private double wingspan;

    @Override
    public String toString() {
        return "Plane{" +
                "name=" + getName() +
                ", getType=" + getType() +
                ", wingspan=" + wingspan +
                "} ";
    }
}
