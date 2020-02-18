package com.test.service;

import com.test.ServerApplicantTestApplication;
import com.test.domainobject.ManufacturerDO;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;
import com.test.service.manufacturer.ManufacturerServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author anup on 2020-01-10 01:51
 * @project spring-hibernate-example
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplicantTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManufacturerTest
{
    @Autowired
    ManufacturerServiceImpl service;
    private ManufacturerDO manufacturerDO = null;


    @Before
    public void init()
    {
        manufacturerDO = new ManufacturerDO();
        manufacturerDO.setManufacturerName("Bentley");
        manufacturerDO.setLogo("Bentley_logo");
    }


    @After
    public void exit()
    {
    }


    @Test
    public void getManufacturerById() throws EntityNotFoundException, ConstraintsViolationException
    {
        ManufacturerDO result = service.find(1L);
        assertEquals("Mercedes-Benz", result.getManufacturerName());
    }


    @Test
    public void getAllManufacturer() throws EntityNotFoundException, ConstraintsViolationException
    {
        Collection<ManufacturerDO> result = service.getAll();
        assertEquals(3, result.size());
    }


    @Test
    public void testUpdateManufacture() throws EntityNotFoundException

    {
        manufacturerDO.setLogo("Bentley_new_logo");
        service.update(4L, manufacturerDO);
        ManufacturerDO result = service.find(4L);
        assertEquals("Bentley_new_logo", result.getLogo());

    }


    @Test
    public void testCreateManufacture() throws EntityNotFoundException, ConstraintsViolationException

    {
        service.create(manufacturerDO);
        ManufacturerDO result = service.find(4L);
        assertEquals("Bentley_logo", result.getLogo());

    }


    @Test
    public void testDeleteManufacture() throws EntityNotFoundException, ConstraintsViolationException

    {
        service.delete(4L);
        ManufacturerDO result = service.find(4L);
        assertEquals(true, result.getDeleted());

    }
}
