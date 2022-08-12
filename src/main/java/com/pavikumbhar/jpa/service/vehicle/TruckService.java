package com.pavikumbhar.jpa.service.vehicle;

import com.pavikumbhar.jpa.dto.vehicle.Truck;
import com.pavikumbhar.jpa.dto.vehicle.Vehicle;
import com.pavikumbhar.jpa.enums.VehicleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TruckService implements GenericVehicleService{
    @Override
    public String getType() {
        return VehicleType.TRUCK.name();
    }
    @Override
    public void validate(Vehicle vehicle) {
        logger.info("Truck:validate");
        classCast(Truck.class,vehicle);
        }

    @Override
    public Vehicle create(Vehicle vehicle) {
        logger.info("Truck:create");
        Truck truck =classCast(Truck.class,vehicle);
        logger.info("Truck:create");
        return truck;
    }

    @Override
    public Vehicle update(long id,Vehicle vehicle) {
        logger.info("Truck:update");
        Truck truck = classCast(Truck.class,vehicle);
        logger.info("Truck:update");
        return truck;
    }
}
