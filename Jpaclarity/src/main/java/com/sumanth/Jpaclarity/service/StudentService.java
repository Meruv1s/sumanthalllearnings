package com.sumanth.Jpaclarity.service;

import com.sumanth.Jpaclarity.DTO.StudentDTO;
import com.sumanth.Jpaclarity.DTO.StudentwithZipcode;
import com.sumanth.Jpaclarity.Repository.StudentRepository;
import com.sumanth.Jpaclarity.entity.Address;
import com.sumanth.Jpaclarity.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(StudentDTO studentDTO) {
        Address address = new Address();
        address.setCity(studentDTO.getCity());
        address.setStreet(studentDTO.getStreet());
        address.setZipcode(studentDTO.getZipcode());
        address.setState(studentDTO.getState());

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        student.setAddress(address);

        return studentRepository.save(student);
    }

    public List<Student> getStudentsByCity(String city) {
        return studentRepository.findByAddress_City(city);
    }
    public List<StudentwithZipcode> getAllStudentsWithZipcodes() {
        // Fetch all students
        List<Student> students = studentRepository.findAll();

        // Map students to a custom DTO
        return students.stream()
                .map(student -> new StudentwithZipcode(student.getName(), student.getAddress().getZipcode()))
                .collect(Collectors.toList());
    }
    public List<Student> getStudentsByZipcode(String zipcode) {
        return studentRepository.findByAddressZipcode(zipcode);
    }
}
