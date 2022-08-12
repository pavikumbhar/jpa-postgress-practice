package com.pavikumbhar.jpa.service.vehicle;



import com.pavikumbhar.jpa.dto.vehicle.Vehicle;
import com.pavikumbhar.jpa.enums.VehicleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public  class VehicleService {

    private final VehicleFactory vehicleFactory;

    public  void validate(Vehicle vehicle){
        GenericVehicleService genericVehicleService = vehicleFactory.getInstance(VehicleType.valueOf(vehicle.getType()).name());
        genericVehicleService.validate(vehicle);
    }

    public  Vehicle create(Vehicle vehicle){
        GenericVehicleService genericVehicleService = vehicleFactory.getInstance(VehicleType.valueOf(vehicle.getType()).name());
        return genericVehicleService.create(vehicle);
    }

    public  Vehicle update(long id,Vehicle vehicle){
        GenericVehicleService genericVehicleService = vehicleFactory.getInstance(VehicleType.valueOf(vehicle.getType()).name());
        return genericVehicleService.update(id,vehicle);
    }



}
