package com.example.gestionquestion.dto;

import com.example.gestionquestion.entities.Reponse;
import com.example.gestionquestion.entities.questionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor

public class QuestionDTO {
    private String enonce;
    private questionType type;
    private List<Reponse> listReponseJuste;
    private List<Reponse> listProposition;

}
