package com.example.gestionquestion.repository;

import com.example.gestionquestion.entities.Question;
import com.example.gestionquestion.entities.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepo extends JpaRepository<Reponse,Integer> {

}
