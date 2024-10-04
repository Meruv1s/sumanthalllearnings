package com.sumanth.stock_calculator.rest;

import com.sumanth.stock_calculator.feignclient.StockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class controller {
    @Autowired
    private StockClient service;

    @GetMapping("calc/{companyname}/{quantity}")
    public ResponseEntity<?> calculate(@PathVariable String companyname ,@PathVariable Integer quantity)
    {
        ResponseEntity<?> responseEntity=null;
        Double totalprice=null;

  try{
    responseEntity= service.getStockPrice(companyname);
    int status= responseEntity.getStatusCode().value();
    if(status==200)
    {
       Double price=(Double) responseEntity.getBody();
       totalprice=price*quantity;
       String response="The total pricr is "+ totalprice;
    responseEntity=   new ResponseEntity<>(response, HttpStatus.OK);
    }
  }

catch (Exception e)
{
    responseEntity=new ResponseEntity<String>("Company not found",HttpStatus.OK);
}
        return responseEntity;
}



}
