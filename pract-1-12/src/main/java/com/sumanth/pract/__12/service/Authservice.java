package com.sumanth.pract.__12.service;

import com.sumanth.pract.__12.dto.Data;
import org.springframework.stereotype.Service;

@Service

public class Authservice {
    public String post(Data d) {

        String email="sumanthmp96@gmail.com";
        String password="Sumanth@123";
        if(d.getEmail().equals(email) && d.getPassword().equals(password))
        {
            return "success";
        }
        else{
            return "Invalid cred";
        }
    }
}
