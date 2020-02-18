package com.test.service;

import com.test.ServerApplicantTestApplication;
import com.test.domainobject.CarDO;
import com.test.domainobject.ManufacturerDO;
import com.test.domainvalue.EngineType;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;
import com.test.service.car.CarServiceImpl;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author anup on 2020-01-10 01:51
 * @project spring-hibernate-example
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplicantTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarServiceTest
{

    @Autowired
    CarServiceImpl service;
    private CarDO carDO = null;


    @Before
    public void init()
    {
        ManufacturerDO manufacturerDO = new ManufacturerDO();
        manufacturerDO.setId(1L);
        carDO = new CarDO("testLicensePlate", 4, true,
            EngineType.ELECTRIC, "XL", manufacturerDO, 1.5d);
    }


    @Test
    public void testGetCarById() throws EntityNotFoundException
    {
        CarDO carDO = service.find(1L);
        assertEquals("DL-7SX-2805", carDO.getLicensePlate());

    }


    @Test
    public void testCreateCar() throws EntityNotFoundException, ConstraintsViolationException
    {
        CarDO carDO = service.create(this.carDO);
        assertEquals("testLicensePlate", carDO.getLicensePlate());
    }


    @Test(expected = ConstraintsViolationException.class)
    public void testCreateCar_Manufacture_negative() throws EntityNotFoundException, ConstraintsViolationException
    {
        carDO.getManufacturerDO().setId(10L);
        service.create(carDO);

    }


    @Test
    @Transactional
    public void testUpdateCar() throws EntityNotFoundException
    {
        carDO.getManufacturerDO().setId(3L);
        service.updateCar(4L, carDO);
        CarDO carDO = service.find(4L);
        assertEquals("Nissan", carDO.getManufacturerDO().getManufacturerName());
    }


    @Test
    public void testDeleteCar() throws EntityNotFoundException
    {
        service.delete(4L);
        CarDO carDO = service.find(4L);
        assertTrue(carDO.getDeleted());
    }

}
