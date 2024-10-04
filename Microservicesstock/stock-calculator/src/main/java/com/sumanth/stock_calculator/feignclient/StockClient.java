package com.sumanth.stock_calculator.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("STOCK-SERVICE")
public interface StockClient {
    @GetMapping("api/stockPrice/{companyName}")

    public ResponseEntity<Double> getStockPrice(@PathVariable String companyName);
}
