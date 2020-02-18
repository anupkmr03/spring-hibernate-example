package com.test.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.domainvalue.EngineType;

import javax.validation.constraints.NotNull;

/**
 * @author anup on 2020-01-07 00:38
 * @project spring-hibernate-example
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{

    @JsonIgnore
    private Long id;

    @NotNull(message = "license plate can't be null ")
    private String licensePlate;

    @NotNull(message = "seat Count can't be null")
    private Integer seatCount;

    @NotNull(message = "convertible can't be null")
    private Boolean convertible;

    @NotNull(message = "rating can't be null")
    private Double rating;

    @NotNull(message = "engineType can't be null")
    private EngineType engineType;

    @NotNull(message = "model can't be null")
    private String model;

    @NotNull(message = "manufacturerDTO can't be null")
    private ManufacturerDTO manufacturerDTO;


    private CarDTO()
    {
    }


    private CarDTO(
        Long id, String licensePlate, Integer seatCount, Boolean convertible,
        Double rating, EngineType engineType, String model, ManufacturerDTO manufacturerDTO)
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.model = model;
        this.manufacturerDTO = manufacturerDTO;
    }


    public static CarDTO.CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public Double getRating()
    {
        return rating;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public ManufacturerDTO getManufacturerDTO()
    {
        return manufacturerDTO;
    }


    public String getModel()
    {
        return model;
    }


    public static class CarDTOBuilder
    {
        @JsonIgnore
        private Long id;

        private String licensePlate;

        private Integer seatCount;

        private Boolean convertible;

        private Double rating;

        private EngineType engineType;

        private String model;

        private ManufacturerDTO manufacturerDTO;


        public CarDTO.CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarDTO.CarDTOBuilder setSeatCount(Integer seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }


        public CarDTO.CarDTOBuilder setSeatCount(String model)
        {
            this.model = model;
            return this;
        }


        public CarDTO.CarDTOBuilder setConvertible(Boolean convertible)
        {
            this.convertible = convertible;
            return this;
        }


        public CarDTO.CarDTOBuilder setRating(Double rating)
        {
            this.rating = rating;
            return this;
        }


        public CarDTO.CarDTOBuilder setEngineType(EngineType engineType)
        {
            this.engineType = engineType;
            return this;
        }


        public CarDTO.CarDTOBuilder setManufacturerDTO(ManufacturerDTO manufacturerDTO)
        {
            this.manufacturerDTO = manufacturerDTO;
            return this;
        }


        public CarDTO.CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate, seatCount, convertible, rating, engineType, model,
                manufacturerDTO);
        }

    }
}
