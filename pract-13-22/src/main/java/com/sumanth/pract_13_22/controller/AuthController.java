package com.sumanth.pract_13_22.controller;

import com.sumanth.pract_13_22.entity.User;
import com.sumanth.pract_13_22.pojo.ForgotPasswordApiData;
import com.sumanth.pract_13_22.pojo.LoginApiData;
import com.sumanth.pract_13_22.pojo.ResetPasswordApiData;
import com.sumanth.pract_13_22.pojo.SignupApiData;
import com.sumanth.pract_13_22.service.Authservice;
import com.sumanth.pract_13_22.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
   @Autowired
   public Authservice authservice;

   @Autowired
   public EmailService emailService;
    @PostMapping("/create-account")
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody SignupApiData signupApiData) throws Exception
    {   User userDataObject=    authservice.handlecreateaccount(signupApiData);
        Map<String,Object> response= new HashMap<String, Object>() ;
        response.put("result","Success");
        response.put("data",userDataObject);

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

     @PostMapping("/forgot-password")
     public ResponseEntity<Map<String,String>>  forgotpassword( @Valid @RequestBody ForgotPasswordApiData forgotPasswordApiData) throws Exception
     {   authservice.handleForgotPassword(forgotPasswordApiData);
         Map<String, String> responseMap = new HashMap<>();
         responseMap.put("result", "success");
         responseMap.put("message", "We have sen an email with a link to reset password.Please check the spam folder");
         return  ResponseEntity.status(HttpStatus.OK).body(responseMap);
     }
// create path
// receive data and validate data->linkid ,password,confirmpassword
    //check if password and confirm passwor matching->if yes proceed else throe error
    //get user based on link id -> if exists update password else invalid key or expired
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String,String>>  resetpassword(@Valid @RequestBody ResetPasswordApiData resetPasswordApiData) throws Exception
    {   authservice.handleResetPassword(resetPasswordApiData);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("result", "success");
        responseMap.put("message", "your password updated successfully... please Login");
        return  ResponseEntity.status(HttpStatus.OK).body(responseMap);


    }















    @GetMapping("/send-email")
    public ResponseEntity<?> sendEmail() throws Exception
    {

        String fromEmail = "sumanthmp96@gmail.com";
        String toEmail = "sumanth824786@gmail.com";
        String subject = "Test Email using google Smtp";

//        // Sending plain text email
//        String mailBody = "Hi Anji, This is the test email from Spring Boot.";
//        emailService.sendPlainEmail(fromEmail, toEmail, subject, mailBody);
//
//        // Sending HTML email
//        mailBody = "Hi Anji, <br/>" +
//                "<a href='https://www.softwareschool.co'>Click here</a><br/>" +
//                "<b>Regards, <br/>Anji Reddy</b><br/>" +
//                "This is the test email from Spring Boot application. Please use the link above to reset your password.";
//
//        emailService.sendHtmlEmail(fromEmail, toEmail, subject, mailBody);
    //    emailService.sendTemplateEmail(fromEmail,toEmail,subject,"mailtext");

        // Preparing the response
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("result", "success");
        responseMap.put("message", "Email sent");

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

}
