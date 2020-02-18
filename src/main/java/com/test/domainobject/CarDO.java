package com.test.domainobject;

import com.test.domainvalue.EngineType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_license_plate", columnNames = {"licensePlate"})
)
public class CarDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "license plate can't be null ")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "seat Count can't be null")
    private Integer seatCount;

    @Column(nullable = false, columnDefinition = "boolean default false")
    @NotNull(message = "convertible can't be null")
    private Boolean convertible = false;

    @Column(nullable = false, columnDefinition = "float default 0.0")
    @NotNull(message = "rating can't be null")
    private Double rating;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull(message = "engineType can't be null")
    private EngineType engineType;

    @Column
    @NotNull(message = "model can't be null")
    private String model;

    @ManyToOne(fetch = FetchType.LAZY,
        optional = false)
    @JoinColumn(name = "manufacturerId", referencedColumnName = "Id")
    @NotNull(message = "manufacturerId can't be null")
    private ManufacturerDO manufacturerDO;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime carUpdatedDate = ZonedDateTime.now();

    @OneToOne(mappedBy = "car")
    private DriverDO driver;


    public CarDO()
    {

    }


    private CarDO(String licensePlate, Integer seatCount)
    {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.deleted = false;
    }


    public CarDO(
        String licensePlate, Integer seatCount, Boolean convertible,
        EngineType engineType, String model, ManufacturerDO manufacturerDO, Double rating)
    {
        this.licensePlate = licensePlate;
        this.convertible = convertible;
        this.seatCount = seatCount;
        this.deleted = false;
        this.engineType = engineType;
        this.model = model;
        this.manufacturerDO = manufacturerDO;
        this.rating = rating;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(Integer seatCount)
    {
        this.seatCount = seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public void setConvertible(Boolean convertible)
    {
        this.convertible = convertible;
    }


    public Double getRating()
    {
        return rating;
    }


    public void setRating(Double rating)
    {
        this.rating = rating;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public void setEngineType(EngineType engineType)
    {
        this.engineType = engineType;
    }


    public String getModel()
    {
        return model;
    }


    public void setModel(String model)
    {
        this.model = model;
    }


    public ManufacturerDO getManufacturerDO()
    {
        return manufacturerDO;
    }


    public void setManufacturerDO(ManufacturerDO manufacturerDO)
    {
        this.manufacturerDO = manufacturerDO;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public ZonedDateTime getCarUpdatedDate()
    {
        return carUpdatedDate;
    }


    public DriverDO getDriver()
    {
        return driver;
    }


    public void setDriver(DriverDO driver)
    {
        this.driver = driver;
    }


    public void setCarUpdatedDate(ZonedDateTime carUpdatedDate)
    {
        this.carUpdatedDate = carUpdatedDate;
    }
}
