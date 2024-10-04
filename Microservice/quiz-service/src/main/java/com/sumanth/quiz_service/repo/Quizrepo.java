package com.sumanth.quiz_service.repo;

import com.sumanth.quiz_service.model.Quiz;
import com.sumanth.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Quizrepo extends JpaRepository<Quiz, Long> {


}
