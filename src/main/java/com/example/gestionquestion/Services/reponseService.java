package com.example.gestionquestion.Services;

import com.example.gestionquestion.entities.Question;
import com.example.gestionquestion.entities.Reponse;
import com.example.gestionquestion.repository.QuestionRepo;
import com.example.gestionquestion.repository.ReponseRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class reponseService {
    private QuestionRepo questionRepo;
    private ReponseRepo reponseRepo;

    @Transactional
    public void addReponsesEnvoye (List<Reponse> r, int questionId){

        Question question =  this.questionRepo.findById(questionId)
                .orElseThrow(()-> new RuntimeException("No question with this id"));

        question.setReponsesEnvoye(r);

        r.forEach(reponse->{
            reponse.setQuestion(question);
            this.reponseRepo.save(reponse);
        });

        System.out.println("QuestionsAdded");

    }

    @Transactional
    public void addReponsesPropose (List<Reponse> r, int questionId){

        Question question =  this.questionRepo.findById(questionId)
                .orElseThrow(()-> new RuntimeException("No question with this id"));

        System.out.println(question.getQuestionId());
        System.out.println(r.size());

        question.setPropositions(r);

        r.forEach(reponse->{
            reponse.setQuestion(question);
            this.reponseRepo.save(reponse);
        });



        System.out.println("QuestionsAdded");
    }
    @Transactional
    public void addReponseJustes (List<Reponse> r, int questionId){

        Question question =  this.questionRepo.findById(questionId)
                .orElseThrow(()-> new RuntimeException("No question with this id"));

        question.setReponsesJustes(r);

        r.forEach(reponse->{
            reponse.setQuestion(question);
            this.reponseRepo.save(reponse);
        });
        this.questionRepo.save(question);
        System.out.println("QuestionsAdded");

    }






}
