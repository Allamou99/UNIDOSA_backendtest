package com.example.gestionquestion.controller;


import com.example.gestionquestion.dto.QuestionDTO;
import com.example.gestionquestion.entities.Question;
import com.example.gestionquestion.entities.Reponse;
import com.example.gestionquestion.entities.questionType;
import com.example.gestionquestion.Services.questionService;
import com.example.gestionquestion.Services.reponseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/questions")
@RestController
@AllArgsConstructor
public class QuestionController {
    private questionService questionService;
    private reponseService reponseService;

    @PostMapping("/initquestion")
    public void InitQuestionReponse(){
        Question q1 = Question.builder()
                .questionType(questionType.QCM)
                .enonce("Quelle heure est il ?")
                .build();

        this.questionService.createQuestion(q1);


        Reponse prop1 = Reponse.builder()
                .question(q1)
                .reponse("21H")
                .build();
        Reponse prop2 = Reponse.builder()
                .question(q1)
                .reponse("22H")
                .build();

        List<Reponse> propositions = new ArrayList<>();
        propositions.add(prop1);
        propositions.add(prop2);



        Reponse reponseVrai = Reponse.builder()
                .question(q1)
                .reponse("19H")
                .build();

        List<Reponse> reponsesVrai = new ArrayList<>();
        reponsesVrai.add(reponseVrai);

        this.reponseService.addReponsesPropose(propositions,q1.getQuestionId());
        this.reponseService.addReponseJustes(reponsesVrai,q1.getQuestionId());


    }
    @PostMapping("/addQuestion")
    public void AddQuestion(@RequestBody QuestionDTO questionDTO){
        this.questionService.addQuestion(questionDTO);
    }

    @GetMapping()
    public String getHello(){
        return "hello world";
    }

    @GetMapping("/propositions/{questionId}")
    public List<Reponse> allProposition(@PathVariable("questionId") int questionId){
        return this.questionService.getAllPropositions(questionId);
    }

}
