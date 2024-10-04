package com.sumanth.quiz_service.service;

import com.sumanth.quiz_service.exception.Stocknotfound;
import com.sumanth.quiz_service.feign.QuizInterface;
import com.sumanth.quiz_service.model.QuestionWrapper;
import com.sumanth.quiz_service.model.Quiz;

import com.sumanth.quiz_service.model.Response;
import com.sumanth.quiz_service.repo.Quizrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    Quizrepo quizrepo;
    @Autowired
  QuizInterface quizInterface;


    public String createquiz(String category, int no, String title) {
   List<Long> question= quizInterface.createquiz(category,no).getBody();
      Quiz quiz = new Quiz();
      quiz.setTitle(title);
     quiz.setQuestionids(question);
       quizrepo.save(quiz);
   return "Success";
    }



    public ResponseEntity<List<QuestionWrapper>> getquizquest(Long id) {
        Quiz quiz=  quizrepo.findById(id).orElseThrow(()->new Stocknotfound("quiz not found"));
       List<Long> r= quiz.getQuestionids();
   return  quizInterface.getQuestionsFromIds(r);

    }

    public ResponseEntity<Integer> submitform(List<Response> responses, Long id) {

ResponseEntity<Integer> score=quizInterface.getScore(responses);

       return  score;
    }
}
