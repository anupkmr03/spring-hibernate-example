package com.test.service.car;

import com.test.domainobject.CarDO;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;

/**
 */
public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    void updateCar(long carId, CarDO carDo) throws EntityNotFoundException;

}
