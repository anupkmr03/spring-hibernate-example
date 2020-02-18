package com.test.controller.mapper;

import com.test.datatransferobject.CarDTO;
import com.test.domainobject.CarDO;

/**
 * @author anup on 2020-01-07 00:41
 * @project spring-hibernate-example
 */
public class CarMapper
{

    public static CarDO makeCarDO(CarDTO carDTO)
    {

        return new CarDO(carDTO.getLicensePlate(), carDTO.getSeatCount(),
            carDTO.getConvertible(), carDTO.getEngineType(), carDTO.getModel(),
            ManufacturerMapper.makeManufacturerDO(carDTO.getManufacturerDTO()), carDTO.getRating());
    }


    public static CarDTO makeCarDTO(CarDO carDO)
    {
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
            .setId(carDO.getId()).setConvertible(carDO.getConvertible())
            .setEngineType(carDO.getEngineType()).setLicensePlate(carDO.getLicensePlate())
            .setManufacturerDTO(ManufacturerMapper.makeManufacturerDTO(carDO.getManufacturerDO()))
            .setRating(carDO.getRating());

        return carDTOBuilder.createCarDTO();
    }

}
