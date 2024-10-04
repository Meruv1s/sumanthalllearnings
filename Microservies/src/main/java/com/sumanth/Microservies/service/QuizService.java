package com.sumanth.Microservies.service;

import com.sumanth.Microservies.model.Question;
import com.sumanth.Microservies.model.QuestionWrapper;
import com.sumanth.Microservies.model.Quiz;
import com.sumanth.Microservies.model.Response;
import com.sumanth.Microservies.repo.Questionrepo;
import com.sumanth.Microservies.repo.Quizrepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
     Quizrepo quizrepo;
    @Autowired
    Questionrepo questionrepo;


    public String createquiz(String category, int no, String title) {
    List<Question> question= questionrepo.findRandomQuestions(category,no);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(question);
        quizrepo.save(quiz);
   return "Success";
    }

    public Quiz getquixbytitle(String title) {

       return quizrepo.getQuizByTitle(title);



    }

    public List<QuestionWrapper> getquizquest(Long id) {
        Optional<Quiz> quiz=  quizrepo.findById(id);
         List<Question> questions=quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers= new ArrayList<QuestionWrapper>();
        for(Question q:questions)
        {
            QuestionWrapper questionWrapper= new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           questionWrappers.add(questionWrapper);
        }
        return questionWrappers;
    }

    public Integer submitform(List<Response> responses, Long id) {

       Quiz quiz= quizrepo.findById(id).orElseThrow(()->new RuntimeException("quiznotfound"));
       List<Question> questions=quiz.getQuestions();
       int right=0;

       for(Response r:responses)
       {
           for(Question q:questions)
           {

                  if(r.getId().equals(q.getId())) {
                      if(r.getResponse().equals(q.getRightAnswer()))
                      {
                      right++;
                  }
              }
           }
       }
       return right;
    }
}
