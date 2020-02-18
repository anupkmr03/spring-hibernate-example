package com.test.domainvalue.formatter;

/**
 * @author anup on 2020-01-08 23:21
 * @project spring-hibernate-example
 */
public interface Formatter
{
    default String format(Object obj)
    {
        return obj.toString();
    }
}
