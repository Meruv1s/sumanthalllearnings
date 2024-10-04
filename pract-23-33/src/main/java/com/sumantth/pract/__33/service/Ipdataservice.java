package com.sumantth.pract.__33.service;

import com.sumantth.pract.__33.pojo.Ipdata;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Service
public class Ipdataservice {

    @Value("${ipinfo.token}")
    public String ipinfotoken;

    @Autowired
    private RestTemplate restTemplate;

    public Ipdata getipdata(String ipaddress)
    {

        try
        { ipaddress= "108.88.174.110";//my ip address because loacal one shows 0.00.0.0.0
            System.out.println(ipaddress);

            //ipinfo website -> signup with google
            // ->open docs and get their domain name-> ipinfo.io/8.8.8.8?token=5f66563b563ba3
            //where ipinfo.io is their domain name
            //ip address=8.8.8.8
            //security key =token=5f66563b563ba3
            //call api using rest template
            //   RestTemplate restTemplate= new RestTemplate(); remove this and create a config file and use bean of it
            // in an api we have -> url,method type,inputdata,response
            //inputdatatype
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String inputdata="";
            HttpEntity<Object> requestentity= new HttpEntity<>(inputdata,headers);
            String apiurl="https://ipinfo.io/"+ipaddress+"?"+ipinfotoken;

            ResponseEntity<Ipdata> response=restTemplate.exchange(apiurl, HttpMethod.GET,requestentity, Ipdata.class);
              Ipdata ipdata=      response.getBody();
            System.out.println(apiurl);
            System.out.println(response.getBody());
            return ipdata;
        }
        catch (HttpClientErrorException e)
        {
            throw new HttpClientErrorException(e.getStatusCode(),e.getMessage());
        }
       }
}
