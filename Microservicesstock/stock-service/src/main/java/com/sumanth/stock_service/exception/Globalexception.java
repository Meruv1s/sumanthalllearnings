package com.sumanth.stock_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Globalexception {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String,String>> handleexceptions(Exception ex)
    {
        Map<String,String> resmap=new HashMap<String,String>();
        resmap.put("message",ex.getMessage());
        resmap.put("status","failed");
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resmap);
    }

    @ExceptionHandler(value = Stocknotfound.class)
    public ResponseEntity<Map<String,String>> handlstockexceptions(Stocknotfound ex)
    {
        Map<String,String> resmap=new HashMap<String,String>();
        resmap.put("stockerrormessage",ex.getMessage());
        resmap.put("statusdorstockerror","failed");
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resmap);
    }


}
