package com.test.controller;

import com.test.controller.mapper.CarMapper;
import com.test.datatransferobject.CarDTO;
import com.test.domainobject.CarDO;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;
import com.test.service.car.CarService;
import com.test.service.manufacturer.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * All CRUD operation for Car.
 * </p>
 */

@RestController
@RequestMapping("v1/cars")
public class CarController
{

    private final CarService carService;


    @Autowired
    public CarController(final CarService carService, final ManufacturerService manufacturerService)
    {
        this.carService = carService;
    }


    @GetMapping("/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public CarDTO getCar(@PathVariable long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }


    @PutMapping("/{carId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCar(@PathVariable long carId, @Valid @RequestBody CarDTO carDTO) throws EntityNotFoundException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        carService.updateCar(carId, carDO);
    }


    @DeleteMapping("/{carId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }

}
