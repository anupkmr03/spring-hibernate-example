package com.test.service.manufacturer;

import com.test.dataaccessobject.ManufacturerRepository;
import com.test.domainobject.ManufacturerDO;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;
import com.test.service.driver.DefaultDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author anup on 2020-01-07 23:33
 * @project spring-hibernate-example
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final ManufacturerRepository manufacturerRepository;


    public ManufacturerServiceImpl(final ManufacturerRepository manufacturerRepository)
    {
        this.manufacturerRepository = manufacturerRepository;
    }


    @Override public ManufacturerDO create(ManufacturerDO manufacturerDO) throws ConstraintsViolationException
    {

        ManufacturerDO manufacturerDO1;
        try
        {
            manufacturerDO1 = manufacturerRepository.save(manufacturerDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a manufacturer: {}", manufacturerDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return manufacturerDO1;
    }


    @Override public ManufacturerDO find(Long manufacturerId) throws EntityNotFoundException
    {
        return findManufacturerChecked(manufacturerId);
    }


    @Override public Collection<ManufacturerDO> getAll() throws EntityNotFoundException
    {
        Iterator<ManufacturerDO> iterable = manufacturerRepository.findAll().iterator();
        Collection<ManufacturerDO> list = new ArrayList<>();
        iterable.forEachRemaining(list::add);
        return list;
    }


    @Override
    @Transactional
    public void update(Long manufacturerId, ManufacturerDO manufacturerDO) throws EntityNotFoundException
    {
        ManufacturerDO manufacturerChecked = findManufacturerChecked(manufacturerId);
        if (manufacturerChecked != null)
        {
            manufacturerDO.setId(manufacturerId);
            manufacturerRepository.save(manufacturerDO);
        }
    }


    @Override
    @Transactional
    public void delete(Long manufacturerId) throws EntityNotFoundException
    {
        ManufacturerDO manufacturerDO = findManufacturerChecked(manufacturerId);
        manufacturerDO.setDeleted(true);
        manufacturerRepository.save(manufacturerDO);
    }


    private ManufacturerDO findManufacturerChecked(Long manufacturerId) throws EntityNotFoundException
    {
        return manufacturerRepository.findById(manufacturerId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find car with id: " + manufacturerId));
    }

}
