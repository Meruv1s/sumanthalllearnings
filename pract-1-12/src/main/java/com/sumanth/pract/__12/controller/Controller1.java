package com.sumanth.pract.__12.controller;

import com.sumanth.pract.__12.dto.Data;
import com.sumanth.pract.__12.dto.Postdto;
import com.sumanth.pract.__12.dto.Updatedto;
import com.sumanth.pract.__12.entity.User;
import com.sumanth.pract.__12.service.Controller1sercice;
import jakarta.validation.Valid;
import org.hibernate.type.descriptor.jdbc.ObjectNullResolvingJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller1 {

    @Autowired
    Controller1sercice controller1sercice;
    @PostMapping("/signup")
    public User signup( @RequestBody Postdto postdto)
    {
        return controller1sercice.signup(postdto);
    }
    @PostMapping("/update")
    public Map<String, String> update(@RequestBody Updatedto updatedto)
    {
        Boolean result=controller1sercice.update(updatedto);
        Map<String,String> responsemap= new HashMap<String,String>();
        if(result==true)
        {
            responsemap.put("result","Success");
            responsemap.put("message" ,"Password updated succesfully");
        }
        else {
            responsemap.put("result","failed");
            responsemap.put("message" ,"unable to find userid");
        }
        return responsemap;
    }
    @PostMapping ("/loginquery")
    public Object loginwithquery( @Valid @RequestBody Data data)
    {
  Object user=    controller1sercice.loginquery(data);
  return user;
    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleexceptions(Exception ex)
//    {
//        Map<String,String> resmap=new HashMap<String,String>();
//        resmap.put("message",ex.getMessage());
//        resmap.put("status","failed");
//return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resmap);
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleexceptions(MethodArgumentNotValidException ex)
//    {  Map<String,String> res=new HashMap<String,String>();
//       ex.getBindingResult().getFieldErrors().forEach(e->
//       {
//           res.put(e.getField(),e.getDefaultMessage());
//       });
//        Map<String,Object> res1=new HashMap<String,Object>();
//        res1.put("message","unable to process req");
//        res1.put("errors",res);
//        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res1);
//    }

}
