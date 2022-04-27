package com.example.gestionquestion.Services;


import com.example.gestionquestion.dto.QuestionDTO;
import com.example.gestionquestion.entities.Question;
import com.example.gestionquestion.entities.Reponse;
import com.example.gestionquestion.repository.QuestionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class questionService {
    private QuestionRepo questionRepo;
    private reponseService reponseService;

    @Transactional
    public void createQuestion(Question q){
        this.questionRepo.save(q);
    }

    public Question getQuestionById(int QuestionId){
        return this.questionRepo.findById(QuestionId)
                .orElseThrow(()-> new RuntimeException("No question with this id"));
    }

    public List<Reponse> getAllPropositions(int QuestionId){
        Question q = this.getQuestionById(QuestionId);
        return q.getPropositions();
    }

    public List<Reponse> getAllReponsesEnvoye(int QuestionId){
        Question q = this.getQuestionById(QuestionId);
        return q.getReponsesEnvoye();
    }

    public List<Reponse> getAllReponsesJuste(int QuestionId){
        Question q = this.getQuestionById(QuestionId);
        return q.getReponsesJustes();
    }

    @Transactional
    public void addQuestion(QuestionDTO qDTO){
        Question question = Question.builder()
                .enonce(qDTO.getEnonce())
                .questionType(qDTO.getType())
                .build();

        this.createQuestion(question);

        int q_id = question.getQuestionId();
        this.reponseService.addReponseJustes(qDTO.getListReponseJuste(),q_id);
        this.reponseService.addReponsesPropose(qDTO.getListProposition(),q_id);
    }

}
