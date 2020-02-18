package com.test.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Request for username and password at time of calling authentication URL
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDTO implements Serializable
{
    private static final long serialVersionUID = -6510964696610437463L;
    @NotNull(message = "username can't be null ")
    private String username;
    @NotNull(message = "password can't be null ")
    private String password;


    public RequestDTO()
    {
    }


    public RequestDTO(String username, String password)
    {
        this.setUsername(username);
        this.setPassword(password);
    }


    public String getUsername()
    {
        return this.username;
    }


    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getPassword()
    {
        return this.password;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }
}
