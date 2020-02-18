package com.test.domainvalue;

/**
 * @author anup on 2020-01-08 23:39
 * @project spring-hibernate-example
 */
public enum Operator
{
    EQUAL("="), GREATER_THEN(">"), NOT_EQUAL("!=");

    private String operatorSymbol;


    Operator(String operatorSymbol)
    {
        this.operatorSymbol = operatorSymbol;
    }


    public String getOperatorSymbol()
    {
        return operatorSymbol;
    }
}
