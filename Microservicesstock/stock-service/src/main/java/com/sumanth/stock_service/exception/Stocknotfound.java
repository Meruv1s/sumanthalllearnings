package com.sumanth.stock_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class Stocknotfound extends RuntimeException{
    public Stocknotfound(String message) {
        super(message);
    }
}
