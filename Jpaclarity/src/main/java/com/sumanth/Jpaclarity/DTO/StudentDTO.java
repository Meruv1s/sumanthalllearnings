package com.sumanth.Jpaclarity.DTO;

import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@AllArgsConstructor
public class StudentDTO {
    private String name;
    private String email;
    private int age;
    private String street;
    private String city;
    private String zipcode;
    private String state;

    // Constructors, Getters and Setters
}