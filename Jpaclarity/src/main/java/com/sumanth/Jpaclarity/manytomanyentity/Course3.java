package com.sumanth.Jpaclarity.manytomanyentity;

import com.sumanth.Jpaclarity.onetomanyentity.Student2;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Course3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String courseName;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Student2> students = new HashSet<>(); // Use Set to avoid duplicates
}
