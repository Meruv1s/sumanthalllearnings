package com.sumanth.pract_13_22.service;

import com.sumanth.pract_13_22.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    /*
    secret key
     add dependencies
     decide what to store in token
     decide expire time
     generate encryped key using secret key
     genearte jwt token
     */

    @Value("${jwt.secret.key}")
    private String jwtsecretkey;
    private int jwttokenvalidity=24*60*60*1000;
    private Key generatesecuritykey()
    {
       return Keys.hmacShaKeyFor(jwtsecretkey.getBytes());
    }

    public String generatejwttoken(User userdata)
    {
        Date tokengeneratetime= new Date();
        Date expirydate= new Date(tokengeneratetime.getTime()+jwttokenvalidity);

        Map<String,Object> tokendata= new HashMap<>();
        tokendata.put("id",userdata.getId());
        tokendata.put("Name",userdata.getName());
        tokendata.put("email",userdata.getEmail());

        String token = Jwts.builder()
                .setClaims(tokendata)  // Use setClaims instead of claim().addClaims()
                .setSubject(userdata.getEmail())  // Set the subject (e.g., the user's email)
                .setIssuedAt(tokengeneratetime)   // Set the issued time
                .setExpiration(expirydate)        // Set the expiration date
                .signWith(generatesecuritykey())  // Sign the token with the security key
                .compact();  // Build the JWT token
    return token;
    }
    public Claims getJwtClaims(String token) {
        SecretKey secretKey = new SecretKeySpec(jwtsecretkey.getBytes(),"HmacSHA256");

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)   // Set the secret key for signature verification
                .build()
                .parseClaimsJws(token)      // Parse the token
                .getBody();                 // Extract the claims

        return claims;
    }

    // Method to verify the token by checking its expiration date
    public Boolean verifyJwtToken(String token) {
        Claims claims = getJwtClaims(token);

        // Check if the token is still valid (not expired)
        Boolean isValid = claims.getExpiration().after(new Date());

        return isValid;
    }

}
