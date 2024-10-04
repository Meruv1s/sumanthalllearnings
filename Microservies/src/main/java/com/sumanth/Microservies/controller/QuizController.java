package com.sumanth.Microservies.controller;

import com.sumanth.Microservies.model.QuestionWrapper;
import com.sumanth.Microservies.model.Quiz;
import com.sumanth.Microservies.model.Response;
import com.sumanth.Microservies.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public String createquiz(@RequestParam  String category,@RequestParam int no, @RequestParam String title)

    {
  return quizService.createquiz(category,no,title);


    }
    @GetMapping("getquiz/{title}")
    public Quiz getquizbytitle(@PathVariable String title)

    {
        return quizService.getquixbytitle(title);


    }
    @GetMapping("getquizbyid/{id}")
    public List<QuestionWrapper> getquizquest(@PathVariable Long id)
    {
      return  quizService.getquizquest(id);
    }

   @PostMapping("submit/{id}")
           public Integer submit(@PathVariable Long id,@RequestBody List<Response> responses)
   {
      return quizService.submitform(responses,id);
   }
}
