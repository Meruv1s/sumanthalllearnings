package com.sumanth.Microservies.repo;

import com.sumanth.Microservies.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Questionrepo extends JpaRepository<Question,Long> {

   List<Question> findByCategory(String name);
   @Query(value = "SELECT * FROM question q WHERE q.category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
   List<Question> findRandomQuestions(String category, int no);

}
