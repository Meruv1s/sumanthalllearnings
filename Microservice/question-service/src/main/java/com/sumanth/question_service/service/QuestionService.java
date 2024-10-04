package com.sumanth.question_service.service;

import com.netflix.discovery.converters.Auto;
import com.sumanth.question_service.model.Question;
import com.sumanth.question_service.model.QuestionWrapper;
import com.sumanth.question_service.model.Response;
import com.sumanth.question_service.repo.Questionrepo;
import com.sumanth.question_service.repo.Questionrepo;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

//// APi calls
    public ResponseEntity<List<Long>> getQuestionsForQuiz(String category, int no) {
        List<Long> question= questionrepo.findRandomQuestions(category,no);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Long> questionids) {
        List<QuestionWrapper> wrappers = new ArrayList<QuestionWrapper>();
        List<Question> questions = new ArrayList<>();
        for (Long i : questionids) {
            questions.add(questionrepo.findById(i).get());
        }
        for (Question q : questions)
        {
            QuestionWrapper wrapper= new QuestionWrapper
                    (q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getscore(List<Response> responses) {

        int right=0;
       for(Response r:responses) {
           Question question = questionrepo.findById(r.getId()).get();
           if(question.getRightAnswer().equals(r.getResponse()))
           {
               right++;
           }
       }
       return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
