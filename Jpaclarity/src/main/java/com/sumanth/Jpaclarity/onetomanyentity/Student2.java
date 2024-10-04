package com.sumanth.Jpaclarity.onetomanyentity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sumanth.Jpaclarity.manytooneentity.Course;
import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private String name;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course", // This is the join table name
            joinColumns = @JoinColumn(name = "student_id"), // Column in join table for Student2
            inverseJoinColumns = @JoinColumn(name = "course_id") // Column in join table for Course2
    )
    private Course2 course;

    // Constructors, Getters and Setters
}
