package com.test.controller.mapper;

import com.test.datatransferobject.ManufacturerDTO;
import com.test.domainobject.ManufacturerDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anup on 2020-01-07 23:28
 * @project spring-hibernate-example
 */
public class ManufacturerMapper
{

    public static ManufacturerDO makeManufacturerDO(ManufacturerDTO manufacturerDTO)
    {
        return new ManufacturerDO(manufacturerDTO.getId(),
            manufacturerDTO.getManufacturerName(), manufacturerDTO.getLogo());
    }


    public static ManufacturerDTO makeManufacturerDTO(ManufacturerDO manufacturerDO)
    {
        ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder = ManufacturerDTO.newBuilder()
            .setId(manufacturerDO.getId()).setManufacturerName(manufacturerDO.getManufacturerName())
            .setLogo(manufacturerDO.getLogo());
        return manufacturerDTOBuilder.createManufacturerDTO();
    }


    public static List<ManufacturerDTO> makeManufacturerDTOList(Collection<ManufacturerDO> manufacturerDOS)
    {
        return manufacturerDOS.stream()
            .map(ManufacturerMapper::makeManufacturerDTO)
            .collect(Collectors.toList());
    }
}
