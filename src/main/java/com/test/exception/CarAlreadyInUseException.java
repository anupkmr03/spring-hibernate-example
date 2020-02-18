package com.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Car in use exception raise conflict if triggered
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Car Already in Use.")
public class CarAlreadyInUseException extends RuntimeException
{
    static final long serialVersionUID = -3387516993334229636L;


    public CarAlreadyInUseException(String message)
    {
        super(message);
    }
}
