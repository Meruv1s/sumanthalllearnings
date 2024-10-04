package com.sumanth.Jpaclarity.Repository;

import com.sumanth.Jpaclarity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.address.city = :city")
    List<Student> findByAddress_City(@Param("city") String city);
    List<Student> findAll();
    List<Student> findByAddressZipcode(String zipcode);
}
