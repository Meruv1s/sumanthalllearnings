package com.sumantth.pract.__33.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private LocalDateTime createdOn=LocalDateTime.now();
    private Boolean isActive=true;
    private Boolean isEmailVerified=false;
   private String passwordResetKey;


}
