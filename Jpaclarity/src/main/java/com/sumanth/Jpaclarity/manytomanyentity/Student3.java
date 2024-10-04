package com.sumanth.Jpaclarity.manytomanyentity;

import com.sumanth.Jpaclarity.onetomanyentity.Course2;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Student3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private String name;
    private int age;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course", // Join table name
            joinColumns = @JoinColumn(name = "student_id"), // Join column in the join table referring to Student2
            inverseJoinColumns = @JoinColumn(name = "course_id") // Join column in the join table referring to Course2
    )
    private Set<Course2> courses = new HashSet<>(); // Use Set to avoid duplicates

}
