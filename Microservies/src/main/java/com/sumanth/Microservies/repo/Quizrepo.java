package com.sumanth.Microservies.repo;

import com.sumanth.Microservies.model.Question;
import com.sumanth.Microservies.model.Quiz;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface Quizrepo extends JpaRepository<Quiz, Long> {

    @Query(value = "SELECT * FROM quiz q WHERE q.title = :title", nativeQuery = true)
    Quiz getQuizByTitle(String title);
}
