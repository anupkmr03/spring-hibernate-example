package com.test.service.manufacturer;

import com.test.domainobject.ManufacturerDO;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;

import java.util.Collection;

/**
 * @author anup on 2020-01-07 23:32
 * @project spring-hibernate-example
 */
public interface ManufacturerService
{
    ManufacturerDO create(ManufacturerDO manufacturerDO) throws ConstraintsViolationException;

    ManufacturerDO find(Long manufacturerId) throws EntityNotFoundException;

    Collection<ManufacturerDO> getAll() throws EntityNotFoundException;

    void update(Long manufacturerId, ManufacturerDO manufacturerDO) throws ConstraintsViolationException, EntityNotFoundException;

    void delete(Long manufacturerId) throws EntityNotFoundException;

}
