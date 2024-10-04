package com.sumanth.Jpaclarity.Repository;

import com.sumanth.Jpaclarity.onetomanyentity.Course2;
import com.sumanth.Jpaclarity.onetomanyentity.Student2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Student2Repo extends JpaRepository<Student2,Long> {
    @Query("SELECT s FROM Student2 s WHERE s.age = :age")
    List<Student2> findStudentsByAge(@Param("age") int age);
    @Query("SELECT s FROM Student2 s WHERE s.course.cid = :courseId")
    List<Student2> findStudentsByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT s.name, c.courseName FROM Student2 s JOIN s.course c")
    List<Object[]> findAllStudentsWithCourseNames();

}
