package com.sumanth.Jpaclarity.Repository;

import com.sumanth.Jpaclarity.manytooneentity.Course;
import com.sumanth.Jpaclarity.manytooneentity.Student1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Courserepo  extends JpaRepository<Course,Long> {
    @Query("SELECT c FROM Course c JOIN c.students s WHERE c.cid = :courseId")
    Course findCourseWithStudents(@Param("courseId") Long courseId);

    @Query("SELECT  c,s.name FROM Course c JOIN c.students s WHERE s.age > :age")
    List<Object[]> findCoursesWithStudentsAboveAge(@Param("age") int age);




}
