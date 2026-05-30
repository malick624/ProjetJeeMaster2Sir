package com.master2sir.entretien.infrastructure.adapter.input.rest;

import com.master2sir.entretien.application.service.ServiceModeChat;
import com.master2sir.entretien.application.usecase.DTOReponse;
import com.master2sir.entretien.application.usecase.DTOQuestion;
import com.master2sir.entretien.application.usecase.CommandeSoumettreReponse;
import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ControleurChat {

    private final ServiceModeChat serviceModeChat;

    @GetMapping("/entretiens/{interviewId}/question-suivante")
    public ResponseEntity<DTOQuestion> obtenirQuestionSuivante(@PathVariable UUID interviewId) {
        Question question = serviceModeChat.obtenirQuestionSuivante(interviewId);
        return ResponseEntity.ok(toDTO(question));
    }

    @PostMapping("/entretiens/{interviewId}/reponses")
    public ResponseEntity<DTOReponse> soumettreReponse(@PathVariable UUID interviewId, @RequestBody CommandeSoumettreReponse commande) {
        commande.setIdEntretien(interviewId);
        Reponse reponse = serviceModeChat.soumettreReponse(commande);
        return ResponseEntity.ok(toDTO(reponse));
    }

    private DTOQuestion toDTO(Question question) {
        return DTOQuestion.builder()
                .id(question.getId())
                .contenu(question.getContent())
                .categorie(question.getCategory())
                .competencesRequises(question.getRequiredSkills())
                .indexOrdre(question.getOrderIndex())
                .build();
    }

    private DTOReponse toDTO(Reponse reponse) {
        return DTOReponse.builder()
                .id(reponse.getId())
                .idQuestion(reponse.getIdQuestion())
                .contenu(reponse.getContenu())
                .evaluation(reponse.getEvaluation())
                .score(reponse.getScore())
                .dateReponse(reponse.getDateReponse())
                .feedback(reponse.getFeedback())
                .build();
    }
}
