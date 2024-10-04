package com.sumanth.Microservies.service;

import com.sumanth.Microservies.model.Question;
import com.sumanth.Microservies.repo.Questionrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    Questionrepo questionrepo;
    public List<Question> getall() {
       return questionrepo.findAll();
    }

    public List<Question> getbycat(String name) {
      List<Question>  catquestions= questionrepo.findByCategory(name);
     return catquestions ;
    }

    public String setallquestions(List<Question> question) {

//    List<Question> question1=
    questionrepo.saveAll(question);

        return "Success";
    }
    public String setonequestions(Question question) {

        Question question1=  questionrepo.save(question);

        return "Success";
    }


}
