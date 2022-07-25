package com.pavikumbhar.jpa.service.vehicle;

import com.pavikumbhar.jpa.dto.vehicle.Vehicle;

public  interface GenericVehicleService {

    public abstract String getType() ;

    public abstract void validate(Vehicle vehicle);

    public abstract Vehicle create(Vehicle vehicle);

    public abstract Vehicle update(long id,Vehicle vehicle);

    public default <T> T  classCast(Class<T> clazz, Object object) {
        return clazz.cast(object);
    }

}
