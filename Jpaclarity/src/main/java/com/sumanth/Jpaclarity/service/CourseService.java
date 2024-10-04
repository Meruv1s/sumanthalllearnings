package com.sumanth.Jpaclarity.service;

import com.sumanth.Jpaclarity.Repository.Courserepo;
import com.sumanth.Jpaclarity.manytooneentity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private Courserepo courseRepo;

    // Method to find a course along with its students based on course ID
    public Course findCourseWithStudents(Long courseId) {
        return courseRepo.findCourseWithStudents(courseId);
    }

    // Method to find courses with students above a certain age
    public List<Object[]> findCoursesWithStudentsAboveAge(int age) {
        return courseRepo.findCoursesWithStudentsAboveAge(age);
    }
}
