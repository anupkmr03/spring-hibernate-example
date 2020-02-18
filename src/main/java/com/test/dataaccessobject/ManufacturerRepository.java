package com.test.dataaccessobject;

import com.test.domainobject.ManufacturerDO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author anup on 2020-01-07 23:35
 * @project spring-hibernate-example
 */
public interface ManufacturerRepository extends CrudRepository<ManufacturerDO, Long>
{
}
