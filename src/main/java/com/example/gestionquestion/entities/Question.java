package com.example.gestionquestion.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int QuestionId;
    @Enumerated(EnumType.STRING)
    private questionType questionType;

    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    private List<Reponse> reponses;


}
