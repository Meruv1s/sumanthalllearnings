package com.sumanth.pract.__12.repo;

import com.sumanth.pract.__12.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface Userrepo extends JpaRepository<User,Long> {

//   Optional< User> findById(long id);
    @Query(value = "Select * from user where email=:emailid and password=:pword",nativeQuery = true)
    Optional<User> dbloginwuery(@Param("emailid") String email, @Param("pword") String password);
    @Transactional
    @Procedure(procedureName = "proc_login")
    Optional<User> dbloginproc(@Param("emailid") String email, @Param("pword") String password);
}
