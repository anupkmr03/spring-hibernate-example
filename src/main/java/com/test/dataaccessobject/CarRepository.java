package com.test.dataaccessobject;

import com.test.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author anup on 2020-01-07 00:27
 * @project spring-hibernate-example
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{
}
