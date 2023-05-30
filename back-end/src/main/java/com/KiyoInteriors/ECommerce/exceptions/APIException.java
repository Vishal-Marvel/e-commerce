package com.KiyoInteriors.ECommerce.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class APIException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public APIException(String message,  HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
