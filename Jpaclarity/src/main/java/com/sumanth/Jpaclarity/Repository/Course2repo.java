package com.sumanth.Jpaclarity.Repository;

import com.sumanth.Jpaclarity.manytooneentity.Course;
import com.sumanth.Jpaclarity.onetomanyentity.Course2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Course2repo extends JpaRepository<Course2,Long> {
//    @Query("SELECT c FROM Course2 c LEFT JOIN FETCH c.students")
//    List<Course2> findAllCoursesWithStudents();
//    @Query("SELECT DISTINCT c FROM Course2 c JOIN c.students s WHERE s.age > :age")
//    List<Course2> findCoursesWithStudentsAboveAge(@Param("age") int age);
//    @Query("SELECT c.courseName, COUNT(s) FROM Course2 c LEFT JOIN c.students s GROUP BY c.cid")
//    List<Object[]> countStudentsInEachCourse();
//    @Query("SELECT c FROM Course2 c LEFT JOIN c.students s WHERE s IS NULL")
//    List<Course2> findCoursesWithoutStudents();
   @Query("SELECT c FROM Course c JOIN c.students s WHERE c.cid = :courseId")
   Course findCourseWithStudents(@Param("courseId") Long courseId);

    @Query("SELECT  c,s.name FROM Course c JOIN c.students s WHERE s.age > :age")
    List<Object[]> findCoursesWithStudentsAboveAge(@Param("age") int age);



}
