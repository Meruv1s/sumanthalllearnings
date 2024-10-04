package com.sumanth.stock_service.stockservice;

import com.sumanth.stock_service.entity.Stockprice;
import com.sumanth.stock_service.exception.Stocknotfound;
import com.sumanth.stock_service.stockrepo.Stockrepo;
import org.hibernate.Remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Stockservice  implements stockser {
    @Autowired
    private Stockrepo stockrepo;


    public Double fetchbycompanyname(String companyname)
    {
     Stockprice sp=   stockrepo.findByCompanyname(companyname);
     if(sp==null)
     {      throw  new Stocknotfound("Stock not found");
     }
     return sp.getCompanyprice();
    }

}
