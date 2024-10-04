package com.sumanth.question_service.controller;

import com.sumanth.question_service.model.Question;
import com.sumanth.question_service.model.QuestionWrapper;
import com.sumanth.question_service.model.Response;
import com.sumanth.question_service.service.QuestionService;
import com.sumanth.question_service.service.QuestionService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

  @GetMapping("allquestions")
    public List<Question> getAllQuestions()
  {
      return questionService.getall();
  }
    @GetMapping("catogory/{name}")
    public List<Question> getbycat(@PathVariable String name)
    {
        return questionService.getbycat(name);
    }
    @PostMapping("allquestions")
    public String postall(@RequestBody  List<Question> question)
    {
        return questionService.setallquestions(question);
    }
    @PostMapping("onequestion")
    public String postone(@RequestBody Question question)
    {
        return questionService.setonequestions(question);
    }


    //// Api resquests from quiz microservice
    @GetMapping("generate")
    public ResponseEntity<List<Long>> createquiz(@RequestParam  String category, @RequestParam int no)

    {
        return questionService.getQuestionsForQuiz(category,no);

    }


       @PostMapping("getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds (@RequestBody List<Long> questionids)
       {

  return  questionService.getQuestionsFromIds(questionids);
       }
    @PostMapping("getscore")
    public ResponseEntity<Integer> getScore (@RequestBody List<Response> responses)
    {

        return  questionService.getscore(responses);
    }

}
