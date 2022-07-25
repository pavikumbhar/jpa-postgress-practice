package com.pavikumbhar.jpa.service.vehicle;

import com.pavikumbhar.jpa.exception.AppException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VehicleFactory {

    private CarService carService;

    private PlanService planService;

    private TruckService truckService;

    private  Map<String, GenericVehicleService> serviceMap;

    public VehicleFactory(CarService carService, PlanService planService,TruckService truckService ){
        this.carService=carService;
        this.planService=planService;
        this.truckService=truckService;
        this.serviceMap = List.of(this.carService,this.planService,this.truckService)
                .stream()
                .collect(Collectors.toMap(GenericVehicleService::getType, Function.identity()));
    }


    public GenericVehicleService getInstance(@NonNull final String name) {
        logger.info("getInstance for name: {}",name);
        logger.debug("serviceMap: {}",serviceMap);
        GenericVehicleService t = serviceMap.get(name);

        if(t == null){
            throw new AppException("Unknown service name: " + name);
        }
        logger.debug("GenericVehicleService for instance: {}",t.getClass().getName());
        return t;
    }
}
