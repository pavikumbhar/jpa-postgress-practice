package com.pavikumbhar.jpa.dto.vehicle;



import javax.validation.constraints.Pattern;

public class UnknownTypeValidator {

    @Pattern(regexp = "CAR|PLANE|TRUCK", message = "invalid type")
    private String type;

}
