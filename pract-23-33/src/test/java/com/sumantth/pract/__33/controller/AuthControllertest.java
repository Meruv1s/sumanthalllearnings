package com.sumantth.pract.__33.controller;

import com.sumantth.pract.__33.pojo.LoginApiData;
import com.sumantth.pract.__33.service.Authservice;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllertest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Authservice authservice;
   @Test
       public void testemailrequiredlogin() throws Exception
       {
           mockMvc.perform(post("/23-33/login")
                           .contentType(MediaType.APPLICATION_JSON)
                           .content("{\"password\":\"Rohit@123\"}"))
                   .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors.email").
                           value("Email Required"));

       }
    @Test
    public void testpasswordrequiredlogin() throws Exception
    {
        mockMvc.perform(post("/23-33/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"sumanth@123\"}"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors.password").
                        value("Password Required"));

    }
    @Test
    public void testbothrequiredlogin() throws Exception
    {
        mockMvc.perform(post("/23-33/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors.email").
                        value("Email Required"))
                .andExpect(jsonPath("$.errors.password").
                        value("Password Required"));

    }
    @Test
    public void testLoginSuccess() throws Exception
    {

        LoginApiData mockloginapidata = new LoginApiData();
        mockloginapidata.setEmail("sumth824786@gmail.com");
        mockloginapidata.setPassword("Rohit@123");
        Map<String ,Object> response=new HashMap<String, Object>();
        // info.put("token",token);
        response.put("userdata","");
        Mockito.when(authservice.handlelogin(mockloginapidata)).thenReturn(response);
        mockMvc.perform(post("/23-33/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"sumanth824786@gail.com\",\"password\":\"Rohit@123\"}"))
                .andExpect(status().isOk());

    }

    @Test
    public void testemailnotregistered() throws Exception {

        // Mock the login data
        LoginApiData mockloginapidata = new LoginApiData();
        mockloginapidata.setEmail("sumanth824786@gmail.com");
        mockloginapidata.setPassword("Rohit@123");

        // Mock service behavior to throw exception when login is called
        Mockito.when(authservice.handlelogin(mockloginapidata))
                .thenThrow(new Exception("Email not registered with us. Please signup"));

        // Perform the POST request and assert expected results
        mockMvc.perform(post("/23-33/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"sumanth824786@gmail.com\",\"password\":\"Rohit@123\"}"))
                .andExpect(status().isBadRequest()) // Expect 400 status
                .andExpect(jsonPath("$.message").value("Email not registered with us. Please signup")) // Expect the message
                .andExpect(jsonPath("$.status").value("failed")); // Expect the status
    }


    @Test
    public void testpasswordnotmatching() throws Exception {

        // Mock the login data
        LoginApiData mockloginapidata = new LoginApiData();
        mockloginapidata.setEmail("sumanth824786@gmail.com");
        mockloginapidata.setPassword("Rohit@123");

        // Mock service behavior to throw exception when login is called
        Mockito.when(authservice.handlelogin(mockloginapidata))
                .thenThrow(new Exception("Password is not matching ,Please try again"));

        // Perform the POST request and assert expected results
        mockMvc.perform(post("/23-33/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"sumanth824786@gmail.com\",\"password\":\"Rohit@123\"}"))
                .andExpect(status().isBadRequest()) // Expect 400 status
                .andExpect(jsonPath("$.message").value("Password is not matching ,Please try again")) // Expect the message
                .andExpect(jsonPath("$.status").value("failed")); // Expect the status
    }

}
