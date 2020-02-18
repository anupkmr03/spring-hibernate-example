package com.test.domainobject;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 */

@Entity
@Table(
    name = "manufacturer",
    uniqueConstraints = @UniqueConstraint(name = "uc_manufacturer_name", columnNames = {"manufacturerName"})
)
public class ManufacturerDO
{
    //Toyota, Volkswagen, Mercedes, Bentley
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "manufacturer Name can't be null ")
    private String manufacturerName;

    @Column(nullable = false)
    @NotNull(message = "logo can't be null")
    private String logo;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updatedDate = ZonedDateTime.now();


    public ManufacturerDO()
    {
    }


    public ManufacturerDO(
        Long id,
        @NotNull(message = "manufacturer Name can't be null ") String manufacturerName, @NotNull(message = "logo can't be null") String logo)
    {
        this.id = id;
        this.manufacturerName = manufacturerName;
        this.logo = logo;
        this.deleted = false;
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


    public String getManufacturerName()
    {
        return manufacturerName;
    }


    public void setManufacturerName(String manufacturerName)
    {
        this.manufacturerName = manufacturerName;
    }


    public String getLogo()
    {
        return logo;
    }


    public void setLogo(String logo)
    {
        this.logo = logo;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public ZonedDateTime getUpdatedDate()
    {
        return updatedDate;
    }


    public void setUpdatedDate(ZonedDateTime updatedDate)
    {
        this.updatedDate = updatedDate;
    }
}
