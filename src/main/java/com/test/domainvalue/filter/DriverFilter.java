package com.test.domainvalue.filter;

import com.test.domainvalue.formatter.Formatter;
import com.test.domainvalue.formatter.StringFormatter;

/**
 * @author anup on 2020-01-08 23:18
 * @project spring-hibernate-example
 */
public enum DriverFilter implements Filter
{
    USERNAME("username", new StringFormatter()),
    STATUS("onlineStatus", new StringFormatter());

    String attributeName;
    Formatter formatter;


    DriverFilter(String attributeName, Formatter formatter)
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
