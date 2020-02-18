package com.test.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO implements Serializable
{
    private static final long serialVersionUID = 2566072514130228588L;
    private final String jwtToken;


    public ResponseDTO(String jwtToken)
    {
        this.jwtToken = jwtToken;
    }


    public String getToken()
    {
        return this.jwtToken;
    }
}
