package com.sumanth.pract.__12.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleexceptions(Exception ex)
    {
        Map<String,String> resmap=new HashMap<String,String>();
        resmap.put("message",ex.getMessage());
        resmap.put("status","failed");
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resmap);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleexceptions(MethodArgumentNotValidException ex)
    {  Map<String,String> res=new HashMap<String,String>();
        ex.getBindingResult().getFieldErrors().forEach(e->
        {
            res.put(e.getField(),e.getDefaultMessage());
        });
        Map<String,Object> res1=new HashMap<String,Object>();
        res1.put("message","unable to process req");
        res1.put("errors",res);
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res1);
    }
}
