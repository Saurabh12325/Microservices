package com.microservice.accounts.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String fieldName, String fieldsName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", fieldName, fieldsName, fieldValue));
    }
}
