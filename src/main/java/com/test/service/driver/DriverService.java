package com.test.service.driver;

import com.test.domainobject.DriverDO;
import com.test.domainvalue.filter.CarFilter;
import com.test.domainvalue.filter.DriverFilter;
import com.test.domainvalue.filter.FilterParam;
import com.test.domainvalue.OnlineStatus;
import com.test.exception.CarAlreadyInUseException;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    void selectCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    void deSelectCar(long driverId) throws EntityNotFoundException;

    Collection<DriverDO> searchDrivers(HashMap<DriverFilter, FilterParam> driverFilters, HashMap<CarFilter, FilterParam> carFilters);
}
