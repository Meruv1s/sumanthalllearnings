package com.sumanth.stock_service.controller;


import com.sumanth.stock_service.stockservice.stockser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Stockcontroller {
    @Autowired
    private  stockser stockser;
    @GetMapping("/stockPrice/{companyName}")

    public ResponseEntity<Double> getStockPrice(@PathVariable String companyName) {

        Double price = stockser.fetchbycompanyname(companyName);

        return new ResponseEntity<>(price, HttpStatus.OK);

    }
}
