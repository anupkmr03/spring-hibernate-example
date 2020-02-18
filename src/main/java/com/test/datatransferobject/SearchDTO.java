package com.test.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.domainvalue.filter.CarFilter;
import com.test.domainvalue.filter.DriverFilter;
import com.test.domainvalue.filter.FilterParam;

import java.util.HashMap;

/**
 * @author anup on 2020-01-08 23:13
 * @project spring-hibernate-example
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchDTO
{
    HashMap<DriverFilter, FilterParam> driverFilters;
    HashMap<CarFilter, FilterParam> carFilters;


    public SearchDTO()
    {

    }


    public SearchDTO(
        HashMap<CarFilter, FilterParam> carFilters, HashMap<DriverFilter, FilterParam> driverFilters)
    {
        this.carFilters = carFilters;
        this.driverFilters = driverFilters;
    }


    public HashMap<CarFilter, FilterParam> getCarFilters()
    {
        return carFilters;
    }


    public void setCarFilters(HashMap<CarFilter, FilterParam> carFilters)
    {
        this.carFilters = carFilters;
    }


    public HashMap<DriverFilter, FilterParam> getDriverFilters()
    {
        return driverFilters;
    }


    public void setDriverFilters(HashMap<DriverFilter, FilterParam> driverFilters)
    {
        this.driverFilters = driverFilters;
    }
}
