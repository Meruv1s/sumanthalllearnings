package com.sumanth.Jpaclarity.manytoonecontroller;

import com.sumanth.Jpaclarity.DTO.sdto;
import com.sumanth.Jpaclarity.manytooneentity.Student1;
import com.sumanth.Jpaclarity.service.Student1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class Student1Controller {

    @Autowired
    private Student1Service student1Service;

    // Endpoint to fetch all students with courses
    @GetMapping("/with-courses")
    public ResponseEntity<List<Student1>> getAllStudentsWithCourses() {
        List<Student1> students = student1Service.getAllStudentsWithCourses();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Endpoint to fetch students by course name
    @GetMapping("/course/{courseName}")
    public ResponseEntity<List<Student1>> getStudentsByCourseName(@PathVariable String courseName) {
        List<Student1> students = student1Service.getStudentsByCourseName(courseName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Endpoint to fetch all students with course details using native query
    @GetMapping("/course-details")
    public ResponseEntity<List<sdto>> getAllStudentsWithCourseDetails() {
        List<sdto> studentDetails = student1Service.getAllStudentsWithCourseDetails();
        return new ResponseEntity<>(studentDetails, HttpStatus.OK);
    }

    // Endpoint to fetch students by course name and age range
    @GetMapping("/course/{courseName}/age-range")
    public ResponseEntity<List<Student1>> getStudentsByCourseNameAndAgeRange(
            @PathVariable String courseName,
            @RequestParam int minAge,
            @RequestParam int maxAge
    ) {
        List<Student1> students = student1Service.getStudentsByCourseNameAndAgeRange(courseName, minAge, maxAge);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Endpoint to group students by course name and count them
    @GetMapping("/group-by-course")
    public ResponseEntity<List<Object[]>> getStudentsGroupedByCourseName() {
        List<Object[]> groupedStudents = student1Service.getStudentsGroupedByCourseName();
        return new ResponseEntity<>(groupedStudents, HttpStatus.OK);
    }
}
