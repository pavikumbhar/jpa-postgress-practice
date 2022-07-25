package com.pavikumbhar.jpa.service.vehicle;

import com.pavikumbhar.jpa.dto.vehicle.Plane;
import com.pavikumbhar.jpa.dto.vehicle.Vehicle;
import com.pavikumbhar.jpa.enums.VehicleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PlanService implements GenericVehicleService {
    @Override
    public String getType() {
        return VehicleType.PLANE.name();
    }
    @Override
    public void validate(Vehicle vehicle) {
        logger.info("Plan:validate");
        classCast(Plane.class,vehicle);


    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        logger.info("Plan:create");
        Plane plane = classCast(Plane.class,vehicle);
        logger.info("Plan:create");
        return plane;
    }

    @Override
    public Vehicle update(long id,Vehicle vehicle) {
        logger.info("Plan:update");
        Plane plane =classCast(Plane.class,vehicle);
        logger.info("Plan:update");
        return plane;
    }
}
