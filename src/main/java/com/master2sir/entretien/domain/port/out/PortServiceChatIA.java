package com.master2sir.entretien.domain.port.out;

import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.RapportEntretien;
import com.master2sir.entretien.domain.model.DescriptionPoste;
import com.master2sir.entretien.domain.model.Question;

import java.util.List;

public interface PortServiceChatIA {
    DescriptionPoste analyserDescriptionPoste(String contenu);
    List<Question> genererQuestions(DescriptionPoste descriptionPoste, int nombreQuestions);
    Reponse evaluerReponse(String question, String reponse, String contexte);
    RapportEntretien genererRapport(List<Reponse> reponses, DescriptionPoste descriptionPoste);
}
