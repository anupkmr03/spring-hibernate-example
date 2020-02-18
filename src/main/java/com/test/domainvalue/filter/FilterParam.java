package com.test.domainvalue.filter;

import com.test.domainvalue.Operator;

/**
 * @author anup on 2020-01-09 00:30
 * @project spring-hibernate-example
 */
public class FilterParam
{
    private Operator operator;
    private String value;


    public FilterParam()
    {

    }


    public FilterParam(Operator operator, String value)
    {
        this.operator = operator;
        this.value = value;
    }


    public Operator getOperator()
    {
        return operator;
    }


    public void setOperator(Operator operator)
    {
        this.operator = operator;
    }


    public Object getValue()
    {
        return value;
    }


    public void setValue(String object)
    {
        this.value = object;
    }
}
