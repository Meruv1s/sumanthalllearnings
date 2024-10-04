package com.sumanth.stock_service.stockservice;

import com.sumanth.stock_service.entity.Stockprice;
import org.springframework.stereotype.Service;


public interface stockser{
    public Double fetchbycompanyname(String companyname);
}
