package com.sumanth.Jpaclarity.service;

import com.sumanth.Jpaclarity.DTO.sdto;
import com.sumanth.Jpaclarity.Repository.Student1reo;
import com.sumanth.Jpaclarity.manytooneentity.Student1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Student1Service {

    @Autowired
    private Student1reo student1Repository;

    // Fetch all students along with their courses
    public List<Student1> getAllStudentsWithCourses() {
        return student1Repository.findAllStudentsWithCourses();
    }

    // Fetch students by course name
    public List<Student1> getStudentsByCourseName(String courseName) {
        return student1Repository.findStudentsByCourseName(courseName);
    }

    // Fetch all student details along with their course names using native query
    public List<sdto> getAllStudentsWithCourseDetails() {

        // Fetch the raw result from the repository
        List<Object[]> result = student1Repository.findAllStudentsWithCourseDetails();

        // Convert the result into List of sdto
        List<sdto> studentList = result.stream().map(obj ->
                new sdto(
                        ((Number) obj[0]).longValue(),  // Assuming sid is of type Number (BigInteger or Long)
                        (String) obj[1],                // name
                        (Integer) obj[2],               // age
                        (String) obj[3]                 // courseName
                )
        ).collect(Collectors.toList());

        // Return the list of sdto
        return studentList;
    }


    // Fetch students by course name and age range
    public List<Student1> getStudentsByCourseNameAndAgeRange(String courseName, int minAge, int maxAge) {
        return student1Repository.findStudentsByCourseNameAndAgeRange(courseName, minAge, maxAge);
    }

    // Group students by course name and count them
    public List<Object[]> getStudentsGroupedByCourseName() {
        return student1Repository.findStudentsGroupedByCourseName();
    }
}
