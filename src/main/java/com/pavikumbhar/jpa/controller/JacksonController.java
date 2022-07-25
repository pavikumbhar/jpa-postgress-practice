package com.pavikumbhar.jpa.controller;

import com.pavikumbhar.jpa.dto.vehicle.Vehicle;
import com.pavikumbhar.jpa.service.vehicle.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JacksonController {

    private final VehicleService vehicleService;

    @PostMapping(value = "/vehicle")
    public ResponseEntity<Vehicle> polymorphicDeserialization(@Valid @RequestBody Vehicle vehicle) {
        logger.info("polymorphicDeserialization: {}",vehicle);
        Vehicle createVehicle= vehicleService.create(vehicle);
        return ResponseEntity.ok(createVehicle);
    }


}
