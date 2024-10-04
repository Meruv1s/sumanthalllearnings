package com.sumanth.pract_13_22.controller;

import com.sumanth.pract_13_22.service.FileUploadService;
import com.sumanth.pract_13_22.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileUploadController {
    @Autowired
 private FileUploadService fileUploadService;
    @Autowired
    private JwtService jwtService;
    /* if incase after sending a token after login if the particular user want to access this method chage in annotationn
    * and in post man use headers and in key palce Authorization and in value give token
    *
    * Afet login
    * receive authorization header check if it is null or not
    * check if it starts with bearer
    * get token without bearer
    * check if it is valid or not
    * check if it is expired or not
    * idf all are ok,proceed to authrization else throw error
    * */
    @PostMapping("/file-upload")
    public ResponseEntity<?> uploadimage(@RequestHeader("Authorization") String jwttoken ,@RequestParam("file")MultipartFile inputfile) throws Exception
    {
        System.out.println(jwttoken);
    if(jwttoken==null || jwttoken.startsWith("Bearer")==false)
    {
        throw new Exception("unauthorized .you are not allowed to do this");
    }
jwttoken=jwttoken.substring(7);

Boolean isvalid=jwtService.verifyJwtToken(jwttoken);

        Claims claims= jwtService.getJwtClaims(jwttoken);
        System.out.println(claims.get("id"));
        System.out.println(claims.get("email"));
//        if (!claims.get("email").equals()) {
//            throw new Exception("Unauthorized: Token does not belong to the user making the request");
//        }
        //=============================
        fileUploadService.handleImageUpload(inputfile);
        Map<String,Object> responsemap=new HashMap<>();
        responsemap.put("result","Success");
        responsemap.put("message","image uploaded successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responsemap);
    }
    @PostMapping("/pdf-upload")
    public ResponseEntity<?> uploadpdfs(@RequestParam("file")MultipartFile inputfile) throws Exception
    {   fileUploadService.handlepdfupload(inputfile);
        Map<String,Object> responsemap=new HashMap<>();
        responsemap.put("result","Success");
        responsemap.put("message","pdf uploaded successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responsemap);
    }
}
/////////////////////////////////////////////////////////////just to check the logic like weather the passed token is passed by right user or not
//@PostMapping("/file-upload")
//public ResponseEntity<?> uploadImage(
//        @RequestHeader("Authorization") String jwttoken,
//        @RequestParam("file") MultipartFile inputFile,
//        @RequestParam("email") String requestEmail) throws Exception {
//
//    System.out.println(jwttoken);
//
//    // Validate token presence and format
//    if (jwttoken == null || !jwttoken.startsWith("Bearer ")) {
//        throw new Exception("Unauthorized: Missing or invalid token");
//    }
//
//    // Strip the "Bearer " prefix from the token
//    jwttoken = jwttoken.substring(7);
//
//    // Verify JWT token and check its validity
//    Boolean isValid = jwtService.verifyJwtToken(jwttoken);
//    if (!isValid) {
//        throw new Exception("Unauthorized: Invalid or expired token");
//    }
//
//    // Extract claims from the token
//    Claims claims = jwtService.getJwtClaims(jwttoken);
//    String tokenEmail = claims.get("email").toString();  // Extract email from token
//    System.out.println("User ID: " + claims.get("id"));
//    System.out.println("User Email: " + tokenEmail);
//
//    // Compare the token's email with the email passed in the request
//    if (!tokenEmail.equals(requestEmail)) {
//        throw new Exception("Unauthorized: Token does not belong to the user making the request");
//    }
//
//    // Validate file input (e.g., size, type)
//    if (inputFile.isEmpty()) {
//        throw new Exception("Invalid file: The file is empty.");
//    }