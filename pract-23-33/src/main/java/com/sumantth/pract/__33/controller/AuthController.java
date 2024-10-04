package com.sumantth.pract.__33.controller;

import com.sumantth.pract.__33.entity.User;
import com.sumantth.pract.__33.pojo.Ipdata;
import com.sumantth.pract.__33.pojo.LoginApiData;
import com.sumantth.pract.__33.pojo.SignupApiData;
import com.sumantth.pract.__33.service.Authservice;
import com.sumantth.pract.__33.service.Fileuploadsercice;
import com.sumantth.pract.__33.service.Ipdataservice;
import com.sumantth.pract.__33.service.s3fileservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/23-33")
public class AuthController {
    @Autowired
    public Authservice authservice;
    @Autowired
    Ipdataservice ipdataservice;

   @Autowired
  Fileuploadsercice fileuploadsercice;

    @PostMapping("/create-account")
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody SignupApiData signupApiData, HttpServletRequest httpServletRequest) throws Exception
    {
        System.out.println(httpServletRequest.getContentType());
        System.out.println(httpServletRequest.getMethod());
        System.out.println(httpServletRequest.getRemoteAddr());

      Ipdata ipdata= ipdataservice.getipdata(httpServletRequest.getRemoteAddr());
        User userDataObject=    authservice.handlecreateaccount(signupApiData);
        Map<String,Object> response= new HashMap<String, Object>() ;
        response.put("result","Success");
        response.put("data",userDataObject);
        response.put("Ipdata",ipdata);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>>  login( @Valid @RequestBody LoginApiData loginApiData) throws Exception
    {
        Map<String,Object> dbuserdata= authservice.handlelogin(loginApiData);


        Map<String,Object> response= new HashMap<String, Object>() ;
        response.put("result","Success");
        response.put("data",dbuserdata);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/pdf-upload")
    public ResponseEntity<?> uploadpdfs(@RequestParam("file") MultipartFile inputfile) throws Exception
    {   fileuploadsercice.handlepdfupload(inputfile);
        Map<String,Object> responsemap=new HashMap<>();
        responsemap.put("result","Success");
        responsemap.put("message","pdf uploaded successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responsemap);
    }
}
