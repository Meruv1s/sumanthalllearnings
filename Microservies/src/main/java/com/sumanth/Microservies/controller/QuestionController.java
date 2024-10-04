package com.sumanth.Microservies.controller;

import com.sumanth.Microservies.model.Question;
import com.sumanth.Microservies.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
