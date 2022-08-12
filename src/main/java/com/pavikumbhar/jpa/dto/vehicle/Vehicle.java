package com.pavikumbhar.jpa.dto.vehicle;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXISTING_PROPERTY ,
            property = "type",visible = true,
            defaultImpl = UnknownVehicleType.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "CAR"),
        @JsonSubTypes.Type(value = Plane.class, name = "PLANE"),
        @JsonSubTypes.Type(value = Truck.class, name = "TRUCK")
})
@Getter
@Setter
public abstract class Vehicle {


    @Pattern(regexp = "CAR|PLANE|TRUCK", message = "invalid type")
    private String type;

    @NotEmpty(message = "name must not be empty")
    private  String name;




}
