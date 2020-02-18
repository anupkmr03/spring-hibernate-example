package com.test.service;

import com.test.ServerApplicantTestApplication;
import com.test.domainobject.DriverDO;
import com.test.domainvalue.OnlineStatus;
import com.test.domainvalue.Operator;
import com.test.domainvalue.filter.DriverFilter;
import com.test.domainvalue.filter.FilterParam;
import com.test.exception.CarAlreadyInUseException;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;
import com.test.service.driver.DefaultDriverService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author anup on 2020-01-10 01:51
 * @project spring-hibernate-example
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplicantTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DriverServiceTest
{

    @Autowired
    DefaultDriverService service;
    private DriverDO driverDO = null;


    @Before
    public void init()
    {
        driverDO = new DriverDO("testDriver", "testPwd");
        driverDO.setOnlineStatus(OnlineStatus.ONLINE);
    }


    @After
    public void exit()
    {
    }


    @Test
    public void testCreateDriver() throws ConstraintsViolationException
    {
        DriverDO driverDO1 = service.create(driverDO);
        assertEquals("testDriver", driverDO1.getUsername());
    }


    @Test
    public void testGetDriverById() throws EntityNotFoundException
    {
        DriverDO driverDO1 = service.find(9L);
        assertEquals("testDriver", driverDO1.getUsername());
    }


    @Test
    public void testUpdateDriver() throws EntityNotFoundException
    {
        service.updateLocation(9L, 43.43, 32.3);
        DriverDO driverDO1 = service.find(9L);
        assertEquals("testDriver", driverDO1.getUsername());
        assertEquals(32.2d, driverDO1.getCoordinate().getLatitude(), 1);
    }


    @Test
    public void testDeleteDriver() throws EntityNotFoundException
    {

        service.delete(9L);
        DriverDO driverDO1 = service.find(9L);
        assertEquals(true, driverDO1.getDeleted());
    }


    @Test
    public void testSelectCar() throws EntityNotFoundException
    {

        service.selectCar(9L, 1L);
        DriverDO driverDO1 = service.find(9L);
        assertEquals("DL-7SX-2805", driverDO1.getCar().getLicensePlate());
    }


    @Test(expected = CarAlreadyInUseException.class)
    public void testSelectCar_Negative() throws EntityNotFoundException
    {

        service.selectCar(8L, 1L);
    }


    @Test
    public void testUnSelectCar() throws EntityNotFoundException
    {

        service.deSelectCar(9L);
        DriverDO driverDO1 = service.find(9L);
        assertNull(driverDO1.getCar());
    }


    @Test
    @Transactional
    public void testSearchDrivers() throws EntityNotFoundException
    {
        HashMap<DriverFilter, FilterParam> param = new HashMap<>();
        FilterParam filterParam = new FilterParam(Operator.EQUAL, "ONLINE");
        param.put(DriverFilter.STATUS, filterParam);
        Collection<DriverDO> driverDOS = service.searchDrivers(param, null);
        assertEquals(5, driverDOS.size());
    }

}
