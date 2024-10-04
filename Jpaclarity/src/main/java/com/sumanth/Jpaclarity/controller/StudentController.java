package com.sumanth.Jpaclarity.controller;

import com.sumanth.Jpaclarity.DTO.StudentDTO;
import com.sumanth.Jpaclarity.DTO.StudentwithZipcode;
import com.sumanth.Jpaclarity.entity.Student;
import com.sumanth.Jpaclarity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // POST request to save a student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = studentService.saveStudent(studentDTO);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // GET request to fetch students by city
    @GetMapping("/city/{city}")
    public List<Student> getStudentsByCity(@PathVariable String city) {
        return studentService.getStudentsByCity(city);
    }
    @GetMapping("/students/zipcodes")
    public List<StudentwithZipcode> getStudentsWithZipcodes() {
        return studentService.getAllStudentsWithZipcodes();
    }
    @GetMapping("/students/zipcodes/{zipcode}")
    public List<Student> getStudentsByZipcode(@PathVariable String zipcode) {
        return studentService.getStudentsByZipcode(zipcode);
    }
}
