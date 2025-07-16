package com.proyecto_ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EcommerceException extends RuntimeException{
    protected String title;
    protected HttpStatus status;

    public EcommerceException(String title, String message, HttpStatus status){
        super(message);
        this.title = title;
        this.status = status;
    }
}
