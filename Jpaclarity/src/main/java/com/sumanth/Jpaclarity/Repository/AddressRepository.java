package com.sumanth.Jpaclarity.Repository;

import com.sumanth.Jpaclarity.DTO.AddressD;
import com.sumanth.Jpaclarity.entity.Address;
import com.sumanth.Jpaclarity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a  WHERE  a.student.age=:age")
    List<Address> findaddressbyage(@Param("age") int age);


}