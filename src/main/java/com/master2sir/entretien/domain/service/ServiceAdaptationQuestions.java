package com.master2sir.entretien.domain.service;

import com.master2sir.entretien.domain.model.DescriptionPoste;
import com.master2sir.entretien.domain.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j 
public class ServiceAdaptationQuestions {

    //cette méthode adapte les questions aux compétences requises dans la description du poste
    public List<Question> adapterQuestionsAuxCompetences(List<Question> questions, DescriptionPoste descriptionPoste) {
        return questions.stream()
                .peek(question -> {
                    if (question.getRequiredSkills() == null || question.getRequiredSkills().isEmpty()) {
                        question.setRequiredSkills(descriptionPoste.getCompetencesRequises());
                    }
                })
                .collect(Collectors.toList());
    }

    public Question adapterQuestionSelonReponsePrecedente(Question questionActuelle, String reponsePrecedente) {
        Question adaptee = Question.builder()
                .id(questionActuelle.getId())
                .content(questionActuelle.getContent())
                .category(questionActuelle.getCategory())
                .requiredSkills(new ArrayList<>(questionActuelle.getRequiredSkills()))
                .orderIndex(questionActuelle.getOrderIndex())
                .context(construireContexteDepuisReponsePrecedente(reponsePrecedente))
                .build();
        return adaptee;
    }

    private String construireContexteDepuisReponsePrecedente(String reponsePrecedente) {
        return "Réponse précédente: " + reponsePrecedente;
    }
}
