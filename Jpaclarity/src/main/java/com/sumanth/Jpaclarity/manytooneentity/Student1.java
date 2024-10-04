package com.sumanth.Jpaclarity.manytooneentity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sumanth.Jpaclarity.DTO.sdto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private String name;

    private int age;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "cid")
    private Course course;

    // Constructors, getters, and setters
}
