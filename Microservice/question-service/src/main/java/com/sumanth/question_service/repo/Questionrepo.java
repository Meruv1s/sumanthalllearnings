package com.sumanth.question_service.repo;

import com.sumanth.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Questionrepo extends JpaRepository<Question,Long> {

   List<Question> findByCategory(String name);
   @Query(value = "SELECT q.id FROM question q WHERE q.category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
   List<Long> findRandomQuestions(String category, int no);

}
