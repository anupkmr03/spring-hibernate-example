package com.test.domainvalue.formatter;

/**
 * @author anup on 2020-01-08 23:24
 * @project spring-hibernate-example
 */
public class StringFormatter implements Formatter
{
    public String format(Object obj)
    {
        return "'" + obj.toString() + "'";
    }
}
