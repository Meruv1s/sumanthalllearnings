package com.sumanth.pract.__12.controller;


import com.sumanth.pract.__12.dto.Data;
import com.sumanth.pract.__12.service.Authservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("Sumanth/v1")
public class Authcontroller {

    ////////////
    @Autowired
    Authservice authservice;

    @GetMapping("/get/{id1}")
    public String get(@RequestParam String a, @PathVariable("id1") long id,@RequestParam String b)
    {
        return a + " "+ b + " "+ id;
    }
    @PostMapping("/post")
    public String post(@RequestParam String a,@RequestParam String b)
    {
        return a + " "+ b + " ";
    }
    @PostMapping("/post1")
    public ResponseEntity< Map<String,String>>  post1(@Valid  @RequestBody Data d, BindingResult validationresult)
    {
        System.out.println(validationresult.hasErrors());
        if(validationresult.hasErrors()==true)
        {
            Map<String,String> errors=new HashMap<>();
        //    System.out.println(validationresult.getFieldError().getField());
            validationresult.getFieldErrors().forEach(e-> {
               errors.put(e.getField(),e.getDefaultMessage());
            } );
            errors.put("validationresult","failed");
            System.out.println(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        }
        else{

        String k= authservice.post(d);
            Map<String,String> response=new HashMap<String,String>();
            response.put("loginresult",k);
            response.put("validation result","success");
            return ResponseEntity.status(HttpStatus.OK).body(response);}
    }
    @PostMapping("/post2")
    public Map<String,Object>  post2(@Valid  @RequestBody Data d, BindingResult validationresult)
    {
        int numbers[]= {1,2,3,4};
        System.out.println(validationresult.hasErrors());
        if(validationresult.hasErrors()==true)
        {
            Map<String,String> errors=new HashMap<>();
            //    System.out.println(validationresult.getFieldError().getField());
            validationresult.getFieldErrors().forEach(e-> {
                errors.put(e.getField(),e.getDefaultMessage());
            } );
            System.out.println(errors);
            Map<String, Object> response= new HashMap<String,Object>();
            response.put("validationresult","failed");
            response.put("erros",errors);
            response.put("hints",numbers);
            return response;
        }
        else{

            String k= authservice.post(d);
            Map<String,Object> response=new HashMap<>();
            response.put("loginresult",k);
            response.put("validation result","success");
            Map<String,String>  userdetails= new HashMap<>();
            userdetails.put("name","sumanth");
            userdetails.put("email",d.getEmail());
            userdetails.put("mobile","68229");
            response.put("userdata",userdetails);

            return response;}
    }
    @PostMapping("/post3")
    public String post( @ModelAttribute Data d,@RequestBody Data d1)
    {
        return d.getEmail() + " "+ d.getPassword() +""+ d1.getEmail() + d1.getPassword();
    }

    @PatchMapping("update1/{id}")// use this if you want to update single cloumn in a row of  table
    public long  patch(@PathVariable long id)
    {
        return id;
    }

    @PutMapping("update2/{id}")// use this if you want to update single cloumn in a row of  table
    public long  put(@PathVariable long id,@RequestBody Data d)
    {
        return id;
    }
    @DeleteMapping("delete/{id}")// use this if you want to update single cloumn in a row of  table
    public String delete(@PathVariable long id)
    {
        return "deleted";
    }


}
