package com.sumanth.Jpaclarity.onetomanyentity;

import com.sumanth.Jpaclarity.manytooneentity.Student1;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;




import lombok.Data;
@Entity
public class Course2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String courseName;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Student2> students = new ArrayList<>();

    // Constructors, Getters, Setters
}