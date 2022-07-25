package com.pavikumbhar.jpa.service.vehicle;

import com.pavikumbhar.jpa.dto.vehicle.Car;
import com.pavikumbhar.jpa.dto.vehicle.Vehicle;
import com.pavikumbhar.jpa.enums.VehicleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class CarService implements GenericVehicleService {

    @Override
    public String getType() {
        return VehicleType.CAR.name();
    }

    @Override
    public void validate(Vehicle vehicle) {
       classCast(Car.class,vehicle);
        logger.info("Car:validate");

    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        logger.info("Car:create");
        Car car = classCast(Car.class,vehicle);
        logger.info("Car:create");
        return car;
    }



    @Override
    public Vehicle update(long id,Vehicle vehicle) {
        logger.info("Car:update");
        Car car = classCast(Car.class,vehicle);
        logger.info("Car:update");
        return car;
    }
}
