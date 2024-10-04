package com.sumanth.pract_13_22.service;

import com.sumanth.pract_13_22.entity.Equipments;
import com.sumanth.pract_13_22.repository.Equipinterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Multithreading {

    @Autowired
    Equipinterface equipinterface;
    @Async//("csvAsyncconfig")// this name is same as that in config multithreadubgonfig class remove it for default configuration
    public void batchupload(List<Equipments> e)

    {
        try {
            System.out.println("batchupload method" + Thread.currentThread().getName());
            equipinterface.saveAll(e);
        }
        catch(Exception e1)
        {
            System.out.println("Inside exceptiom" +e1.getMessage());
            System.out.println("Inside eception" + e);
        }
    }
}
