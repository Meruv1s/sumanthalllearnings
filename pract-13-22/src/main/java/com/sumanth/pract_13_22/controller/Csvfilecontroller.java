package com.sumanth.pract_13_22.controller;

import com.sumanth.pract_13_22.service.Equipservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


/*
Create an entity for storing large csv data
create repo
create service
create end point
 */

@RestController
public class Csvfilecontroller {
    @Autowired
    Equipservice equipservice;

    @PostMapping("/csvtodb")
    public ResponseEntity<Map<String,Object>> csvtodb(@RequestParam("csvfile")MultipartFile filename) throws Exception
    {   equipservice.csvtodb(filename);
         Map<String ,Object> responsemap= new HashMap<String,Object>();
         responsemap.put("result","success");
         responsemap.put("msg","csv file uploaded succesfully");
return new ResponseEntity<>(responsemap, HttpStatus.OK);
    }


}
