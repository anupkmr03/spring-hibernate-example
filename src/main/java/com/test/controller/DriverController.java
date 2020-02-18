package com.test.controller;

import com.test.controller.mapper.DriverMapper;
import com.test.datatransferobject.DriverDTO;
import com.test.datatransferobject.SearchDTO;
import com.test.domainobject.DriverDO;
import com.test.exception.CarAlreadyInUseException;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;
import com.test.service.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;


    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    @ResponseStatus(HttpStatus.OK)
    public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @PutMapping("/{driverId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateLocation(
        @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @DeleteMapping("/{driverId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PatchMapping("select/{driverId}/{carId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void selectCar(@PathVariable long driverId, @PathVariable long carId) throws EntityNotFoundException, CarAlreadyInUseException
    {
        driverService.selectCar(driverId, carId);
    }


    @PatchMapping("deSelect/{driverId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deSelectCar(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.deSelectCar(driverId);
    }


    @PostMapping("find")
    @ResponseStatus(HttpStatus.OK)
    public List<DriverDTO> findDrivers(
        @RequestBody SearchDTO searchDTO)
    {
        return DriverMapper.makeDriverDTOList(driverService.searchDrivers(
            searchDTO.getDriverFilters(),
            searchDTO.getCarFilters()));
    }
}
