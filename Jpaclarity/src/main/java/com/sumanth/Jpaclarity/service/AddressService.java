package com.sumanth.Jpaclarity.service;

import com.sumanth.Jpaclarity.DTO.AddressD;
import com.sumanth.Jpaclarity.Repository.AddressRepository;
import com.sumanth.Jpaclarity.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    // Method to find addresses by student's age


    public List<Address> findAddress(int age) {
        return addressRepository.findaddressbyage(age);
    }
}