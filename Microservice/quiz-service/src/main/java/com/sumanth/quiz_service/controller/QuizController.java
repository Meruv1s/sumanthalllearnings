package com.sumanth.quiz_service.controller;

import com.sumanth.quiz_service.model.*;
import com.sumanth.quiz_service.service.QuizService;
import com.sumanth.quiz_service.model.QuestionWrapper;
import com.sumanth.quiz_service.model.Quiz;
import com.sumanth.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public String createquiz(@RequestBody Quizdto quizdto)

    {
  return quizService.createquiz(quizdto.getCategory(),quizdto.getNo(),quizdto.getTitle());


    }

    @GetMapping("getquizbyid/{id}")
    public ResponseEntity<List<QuestionWrapper>> getquizquest(@PathVariable Long id)
    {
      return  quizService.getquizquest(id);
    }

   @PostMapping("submit/{id}")
           public ResponseEntity<Integer> submit(@PathVariable Long id,@RequestBody List<Response> responses)
   {
      return quizService.submitform(responses,id);
  }
}
