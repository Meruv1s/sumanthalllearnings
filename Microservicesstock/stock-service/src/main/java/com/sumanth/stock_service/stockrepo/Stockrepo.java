package com.sumanth.stock_service.stockrepo;

import com.sumanth.stock_service.entity.Stockprice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Stockrepo extends JpaRepository<Stockprice,Long> {


    Stockprice findByCompanyname(String Companyname);
}
