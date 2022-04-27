package com.example.gestionquestion.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int QuestionId;
    private String enonce;


    @Enumerated(EnumType.STRING)
    private questionType questionType;

    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    private List<Reponse> propositions;

    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    private List<Reponse> reponsesJustes;

    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    private List<Reponse> reponsesEnvoye;



}
