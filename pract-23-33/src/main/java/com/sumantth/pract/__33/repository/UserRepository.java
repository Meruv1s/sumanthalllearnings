package com.sumantth.pract.__33.repository;


import com.sumantth.pract.__33.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Optional<User>   findByPasswordResetKey(String passwordresetkey);
}
