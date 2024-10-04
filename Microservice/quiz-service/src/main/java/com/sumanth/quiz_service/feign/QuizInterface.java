package com.sumanth.quiz_service.feign;

import com.sumanth.quiz_service.model.QuestionWrapper;
import com.sumanth.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Long>> createquiz(@RequestParam String category, @RequestParam int no);




    @PostMapping("question/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds (@RequestBody List<Long> questionids);

    @PostMapping("question/getscore")
    public ResponseEntity<Integer> getScore (@RequestBody List<Response> responses);

}
