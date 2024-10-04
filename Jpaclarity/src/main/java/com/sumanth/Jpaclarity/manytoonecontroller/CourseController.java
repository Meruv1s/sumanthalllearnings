package com.sumanth.Jpaclarity.manytoonecontroller;

import com.sumanth.Jpaclarity.manytooneentity.Course;
import com.sumanth.Jpaclarity.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Endpoint to get a course and its students by course ID
    @GetMapping("/{courseId}/students")
    public ResponseEntity<Course> getCourseWithStudents(@PathVariable Long courseId) {
        Course course = courseService.findCourseWithStudents(courseId);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get courses that have students older than a given age
    @GetMapping("/students/above-age/{age}")
    public ResponseEntity<List<Object[]>> getCoursesWithStudentsAboveAge(@PathVariable int age) {
        List<Object[]> courses = courseService.findCoursesWithStudentsAboveAge(age);

        if (!courses.isEmpty()) {
            return new ResponseEntity<>(courses, HttpStatus.OK);  // Return the list of Object[] if not empty
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if no results found
        }
    }

}
