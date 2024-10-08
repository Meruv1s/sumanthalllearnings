package com.sumanth.pract_13_22.repository;

import com.sumanth.pract_13_22.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Optional<User>   findByPasswordResetKey(String passwordresetkey);
}
