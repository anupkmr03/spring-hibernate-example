package com.test.domainvalue.filter;

import com.test.domainvalue.formatter.Formatter;

/**
 * @author anup on 2020-01-08 23:17
 * @project spring-hibernate-example
 */
public interface Filter
{
    public String getAttributeName();

    public Formatter getFormatter();

}
