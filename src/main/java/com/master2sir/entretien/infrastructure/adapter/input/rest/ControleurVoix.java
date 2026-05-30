package com.master2sir.entretien.infrastructure.adapter.input.rest;

import com.master2sir.entretien.application.service.ServiceModeVoix;
import com.master2sir.entretien.application.usecase.DTOReponse;
import com.master2sir.entretien.application.usecase.DTOQuestion;
import com.master2sir.entretien.application.usecase.CommandeSoumettreReponse;
import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/voix")
@RequiredArgsConstructor
public class ControleurVoix {

    private final ServiceModeVoix serviceModeVoix;

    @GetMapping("/entretiens/{interviewId}/question-suivante")
    public ResponseEntity<DTOQuestion> obtenirQuestionSuivante(@PathVariable UUID interviewId) {
        Question question = serviceModeVoix.obtenirQuestionSuivante(interviewId);
        return ResponseEntity.ok(toDTO(question));
    }

    @GetMapping("/synthetiser")
    public ResponseEntity<byte[]> synthetiserQuestion(@RequestParam String texte) {
        byte[] audio = serviceModeVoix.synthetiserQuestion(texte);
        return ResponseEntity.ok()
                .header("Content-Type", "audio/mpeg")
                .body(audio);
    }

    @PostMapping("/transcrire")
    public ResponseEntity<String> transcrireReponse(@RequestParam("audio") MultipartFile audio) {
        try {
            byte[] donneesAudio = audio.getBytes();
            String transcription = serviceModeVoix.transcrireReponse(donneesAudio);
            return ResponseEntity.ok(transcription);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de la transcription audio");
        }
    }

    @PostMapping("/entretiens/{interviewId}/reponses")
    public ResponseEntity<DTOReponse> soumettreReponse(@PathVariable UUID interviewId, @RequestBody CommandeSoumettreReponse commande) {
        commande.setIdEntretien(interviewId);
        Reponse reponse = serviceModeVoix.soumettreReponse(commande);
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
