package com.test.controller;

import com.test.controller.mapper.ManufacturerMapper;
import com.test.datatransferobject.ManufacturerDTO;
import com.test.domainobject.ManufacturerDO;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;
import com.test.service.manufacturer.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author anup on 2020-01-08 01:09
 * @project spring-hibernate-example
 */
@RestController
@RequestMapping("v1/manufacturer")
public class ManufacturerController
{
    private final ManufacturerService manufacturerService;


    @Autowired
    public ManufacturerController(final ManufacturerService manufacturerService)
    {
        this.manufacturerService = manufacturerService;
    }


    @GetMapping("/{manufacturerId}")
    @ResponseStatus(HttpStatus.OK)
    public ManufacturerDTO getManufacturer(@PathVariable long manufacturerId) throws EntityNotFoundException
    {
        return ManufacturerMapper.makeManufacturerDTO(manufacturerService.find(manufacturerId));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ManufacturerDTO> getAllManufacturer() throws EntityNotFoundException
    {
        return ManufacturerMapper.makeManufacturerDTOList(manufacturerService.getAll());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturerDTO createManufacturer(@Valid @RequestBody ManufacturerDTO manufacturerDTO) throws ConstraintsViolationException
    {
        ManufacturerDO manufacturerDO = ManufacturerMapper.makeManufacturerDO(manufacturerDTO);
        return ManufacturerMapper.makeManufacturerDTO(manufacturerService.create(manufacturerDO));
    }


    @PutMapping("/{manufacturerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateManufacturer(@PathVariable long manufacturerId, @Valid @RequestBody ManufacturerDTO manufacturerDTO)
        throws EntityNotFoundException, ConstraintsViolationException
    {
        ManufacturerDO manufacturerDO = ManufacturerMapper.makeManufacturerDO(manufacturerDTO);
        manufacturerService.update(manufacturerId, manufacturerDO);
    }


    @DeleteMapping("/{manufacturerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteManufacturer(@PathVariable long manufacturerId) throws EntityNotFoundException
    {
        manufacturerService.delete(manufacturerId);
    }
}
