package com.test.service.driver;

import com.test.dataaccessobject.DriverRepository;
import com.test.domainobject.CarDO;
import com.test.domainobject.DriverDO;
import com.test.domainvalue.filter.FilterParam;
import com.test.domainvalue.GeoCoordinate;
import com.test.domainvalue.OnlineStatus;
import com.test.domainvalue.filter.CarFilter;
import com.test.domainvalue.filter.DriverFilter;
import com.test.exception.CarAlreadyInUseException;
import com.test.exception.ConstraintsViolationException;
import com.test.exception.EntityNotFoundException;
import com.test.service.car.CarService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;

    private final EntityManager entityManager;

    private final CarService carService;


    public DefaultDriverService(final DriverRepository driverRepository, final CarService carService, final EntityManager entityManager)
    {
        this.driverRepository = driverRepository;
        this.carService = carService;
        this.entityManager = entityManager;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
        driverRepository.save(driverDO);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
        driverRepository.save(driverDO);
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    @Override
    public void selectCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        CarDO carDO = carService.find(carId);

        if (driverDO.getOnlineStatus() == OnlineStatus.ONLINE)
        {
            driverDO.setCar(carDO);
            try
            {
                driverRepository.save(driverDO);
            }
            catch (DataIntegrityViolationException e)
            {
                throw new CarAlreadyInUseException("This car is already used by some other driver");
            }
        }
    }


    @Override public void deSelectCar(long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCar(null);
        driverRepository.save(driverDO);
    }


    @Override public Collection<DriverDO> searchDrivers(
        HashMap<DriverFilter, FilterParam> driverFilters, HashMap<CarFilter, FilterParam> carFilters)
    {
        StringBuilder sb = new StringBuilder("from DriverDO d ");

        if ((driverFilters != null && !driverFilters.isEmpty()) ||
            (carFilters != null && !carFilters.isEmpty()))
        {
            sb.append("where ");
        }
        else
        {
            return new ArrayList<>();
        }
        if (driverFilters != null && !driverFilters.isEmpty())
            prepareQueryDriver(driverFilters, sb);

        if (carFilters != null && !carFilters.isEmpty())
        {
            sb.append(" and ");
            prepareQueryCar(carFilters, sb);
        }

        System.out.println("Prepared Query --> " + sb.toString());

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(sb.toString());
        List drivers = query.list();
        return drivers;
    }


    private void prepareQueryDriver(HashMap<DriverFilter, FilterParam> map, StringBuilder sb)
    {
        for (Map.Entry<DriverFilter, FilterParam> entry : map.entrySet())
        {
            sb.append("d.").append(entry.getKey().getAttributeName());
            sb.append(entry.getValue().getOperator().getOperatorSymbol());
            sb.append(entry.getKey().getFormatter().format(entry.getValue().getValue()));
        }
    }


    private void prepareQueryCar(HashMap<CarFilter, FilterParam> map, StringBuilder sb)
    {
        for (Map.Entry<CarFilter, FilterParam> entry : map.entrySet())
        {
            sb.append("d.car.").append(entry.getKey().getAttributeName());
            sb.append(entry.getValue().getOperator().getOperatorSymbol());
            sb.append(entry.getKey().getFormatter().format(entry.getValue().getValue()));
        }
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

}
