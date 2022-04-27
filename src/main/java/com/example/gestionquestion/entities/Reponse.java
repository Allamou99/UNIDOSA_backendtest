package com.example.gestionquestion.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@Builder
@Entity
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reponseId;

    @ManyToOne()
    @JoinColumn(name = "question_id" )
    @JsonIgnore
    private Question question;
    private String reponse;
}
