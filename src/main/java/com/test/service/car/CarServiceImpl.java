package com.test.service.car;

import com.test.dataaccessobject.CarRepository;
import com.test.domainobject.CarDO;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;
import com.test.service.driver.DefaultDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author anup on 2020-01-07 00:26
 * @project spring-hibernate-example
 */

@Service
public class CarServiceImpl implements CarService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final CarRepository carRepository;


    public CarServiceImpl(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }


    @Override public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {

        CarDO car;
        try
        {
            car = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a car: {}", carDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }


    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carChecked = findCarChecked(carId);
        carChecked.setDeleted(true);
        carRepository.save(carChecked);
    }


    @Override
    @Transactional
    public void updateCar(long carId, CarDO carDo) throws EntityNotFoundException
    {
        CarDO carChecked = findCarChecked(carId);
        if (carChecked != null)
        {
            carDo.setId(carId);
            carRepository.save(carDo);
        }
    }


    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find car with id: " + carId));
    }
}
