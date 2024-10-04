package com.sumanth.Jpaclarity.controller;

import com.sumanth.Jpaclarity.DTO.AddressD;
import com.sumanth.Jpaclarity.entity.Address;
import com.sumanth.Jpaclarity.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Endpoint to get addresses by student age
    @GetMapping("/by-age/{age}")
    public List<Address> getAddressesByAge(@PathVariable int age) {
        return addressService.findAddress(age);
    }
}