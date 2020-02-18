package com.test.domainvalue.filter;

import com.test.domainvalue.formatter.DefaultFormatter;
import com.test.domainvalue.formatter.Formatter;
import com.test.domainvalue.formatter.StringFormatter;

/**
 * @author anup on 2020-01-08 23:17
 * @project spring-hibernate-example
 */
public enum CarFilter implements Filter
{
    SEAT_COUNT("seatCount", new DefaultFormatter()),
    LICENSE_PLATE("licensePlate", new StringFormatter()),
    RATING("rating", new DefaultFormatter());

    String attributeName;
    Formatter formatter;


    CarFilter(String attributeName, Formatter formatter)
    {
        this.attributeName = attributeName;
        this.formatter = formatter;
    }


    @Override public String getAttributeName()
    {
        return this.attributeName;
    }


    @Override public Formatter getFormatter()
    {
        return this.formatter;
    }
}
