package com.test.util;

/**
 * @author anup on 2020-01-09 23:36
 * @project spring-hibernate-example
 */
public class Constants
{
    public static final String USER_DISABLED = "User is disabled";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hour
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String[] AUTH_WHITELIST = {
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/v2/api-docs",
        "/webjars/**",
    };

}
