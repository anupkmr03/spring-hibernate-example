package com.test.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

/**
 * @author anup on 2020-01-07 22:50
 * @project spring-hibernate-example
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManufacturerDTO
{
    private Long id;

    @NotNull(message = "manufacturer Name can't be null ")
    private String manufacturerName;

    @NotNull(message = "logo can't be null")
    private String logo;


    private ManufacturerDTO()
    {
    }


    private ManufacturerDTO(Long id, String manufacturerName, String logo)
    {
        this.id = id;
        this.manufacturerName = manufacturerName;
        this.logo = logo;
    }


    public static ManufacturerDTO.ManufacturerDTOBuilder newBuilder()
    {
        return new ManufacturerDTOBuilder();
    }


    public Long getId()
    {
        return id;
    }


    public String getManufacturerName()
    {
        return manufacturerName;
    }


    public String getLogo()
    {
        return logo;
    }


    public static class ManufacturerDTOBuilder
    {
        private Long id;
        private String manufacturerName;
        private String logo;


        public ManufacturerDTO.ManufacturerDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public ManufacturerDTO.ManufacturerDTOBuilder setManufacturerName(String manufacturerName)
        {
            this.manufacturerName = manufacturerName;
            return this;
        }


        public ManufacturerDTO.ManufacturerDTOBuilder setLogo(String logo)
        {
            this.logo = logo;
            return this;
        }


        public ManufacturerDTO createManufacturerDTO()
        {
            return new ManufacturerDTO(id, manufacturerName, logo);
        }

    }
}
