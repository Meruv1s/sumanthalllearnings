package com.sumanth.Jpaclarity.Repository;

import com.sumanth.Jpaclarity.DTO.sdto;
import com.sumanth.Jpaclarity.manytooneentity.Student1;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student1reo extends JpaRepository<Student1,Long> {
    @Query("SELECT s FROM Student1 s JOIN s.course c ")
    List<Student1>
    findAllStudentsWithCourses();
    @Query("SELECT s FROM Student1 s WHERE s.course.courseName = :courseName")
    List<Student1> findStudentsByCourseName(@Param("courseName") String courseName);
    @Query(value = "SELECT s.sid, s.name, s.age, c.course_name FROM student1 s JOIN course c ON s.course_id = c.cid", nativeQuery = true)
    List<Object[]> findAllStudentsWithCourseDetails();
    // Native query to fetch student details along with their course names
    @Query("SELECT s FROM Student1 s WHERE s.course.courseName = :courseName AND s.age BETWEEN :minAge AND :maxAge")
    List<Student1> findStudentsByCourseNameAndAgeRange(
            @Param("courseName") String courseName,
            @Param("minAge") int minAge,
            @Param("maxAge") int maxAge
    );
    @Query("SELECT s.course.courseName, COUNT(s) FROM Student1 s GROUP BY s.course.courseName")
    List<Object[]> findStudentsGroupedByCourseName();
}
