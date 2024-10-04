package com.sumantth.pract.__33.service;

import com.sumantth.pract.__33.entity.User;
import com.sumantth.pract.__33.pojo.LoginApiData;
import com.sumantth.pract.__33.pojo.SignupApiData;
import com.sumantth.pract.__33.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.desktop.UserSessionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class Authservice {
    @Autowired
    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    Logger logger= LoggerFactory.getLogger(Authservice.class);// used this to maintain logs

    public User handlecreateaccount(SignupApiData signupApiData) throws  Exception {

        Optional<User> dbdata= userRepository.findByEmail(signupApiData.getEmail());
        if(dbdata.isEmpty())
        {
            User userobj= new User();
            userobj.setName(signupApiData.getName());
            userobj.setEmail(signupApiData.getEmail());
            userobj.setMobile(signupApiData.getMobile());
            userobj.setPassword(passwordEncoder.encode(signupApiData.getPassword()));
            User dbuserdata= userRepository.save(userobj);
            logger.info("ACCOUNT CREATED EMAIL={}",signupApiData.getEmail());
            return dbuserdata;
        }
        else {
            logger.error("ACCOUNT Creation failed EMAIL={}",signupApiData.getEmail());
            throw new Exception("User already exists. Please login");

        }

    }


    public Map<String ,Object> handlelogin(LoginApiData loginApiData) throws Exception{

        Optional<User> dbdata= userRepository.findByEmail(loginApiData.getEmail());


        if(dbdata.isEmpty())
        {
            throw new Exception("Email not registered with us.Please signup");
        }
        else{
            User userdata= dbdata.get();
            Boolean isMatching=     passwordEncoder.matches(loginApiData.getPassword(),userdata.getPassword());
            if(isMatching==true)
            {
               // String token=jwtService.generatejwttoken(userdata);
                Map<String ,Object> info=new HashMap<String,Object>();
               // info.put("token",token);
                info.put("userdata",userdata);
                return info;
            }else{
                throw  new Exception("Password is not matching ,Please try again");
            }
        }
    }
}
