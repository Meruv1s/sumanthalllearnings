package com.sumanth.pract_13_22.service;

import com.sumanth.pract_13_22.entity.User;
import com.sumanth.pract_13_22.pojo.ForgotPasswordApiData;
import com.sumanth.pract_13_22.pojo.LoginApiData;
import com.sumanth.pract_13_22.pojo.ResetPasswordApiData;
import com.sumanth.pract_13_22.pojo.SignupApiData;
import com.sumanth.pract_13_22.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class Authservice {


    @Autowired
    public UserRepository userRepository;
   public PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
   @Autowired
   public EmailService emailService;

@Autowired
private JwtService jwtService;


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
    return dbuserdata;
    }
   else {
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
   String token=jwtService.generatejwttoken(userdata);
               Map<String ,Object> info=new HashMap<String,Object>();
             info.put("token",token);
             info.put("userdata",userdata);
               return info;
           }else{
               throw  new Exception("Password is not matching ,Please try again");
           }
        }
    }

    public void handleForgotPassword(ForgotPasswordApiData forgotPasswordApiData) throws Exception
    {

        Optional<User> dbData = userRepository.findByEmail(forgotPasswordApiData.getEmail());

        if (dbData.isEmpty()) {
           throw new Exception("Email id not registered with us. Please check your email and try again.");
        } else {
            String passwordResetKey = UUID.randomUUID().toString();
            User userData = dbData.get();
            userData.setPasswordResetKey(passwordResetKey);
            userRepository.save(userData);
//
//            String mailBody = "Hi " + userData.getName() + ",<br/>" +
//                    "Please find the below link to reset your password:<br/>" +
//                    "<a href='http://localhost:8080/password-reset-ui?linkid=" + passwordResetKey + "'>" +
//                    "Click here to reset your password</a><br/>" +
//                    "<b>Regards,<br/>Springboot App</b>";

            emailService.sendTemplateEmail("sumanthmp96@gmail.com", userData.getEmail(), "Password Reset Link", "mailtext",userData);
        }
    }

    public void handleResetPassword(ResetPasswordApiData resetPasswordApiData) throws Exception
    {
        if(resetPasswordApiData.getPassword().equals(resetPasswordApiData.getConfirmpassword())==false)
        {
            throw new Exception("password and confirm password should match.");
        }
 Optional<User> dbdata=userRepository.findByPasswordResetKey(resetPasswordApiData.getLinkId());
        if(dbdata.isEmpty())
        {
            throw new Exception("Invalid reset key or Expired");
        }
        User userdata=dbdata.get();
        userdata.setPassword(passwordEncoder.encode((resetPasswordApiData.getPassword())));
        userdata.setPasswordResetKey("");
        userRepository.save(userdata);
    }
}
