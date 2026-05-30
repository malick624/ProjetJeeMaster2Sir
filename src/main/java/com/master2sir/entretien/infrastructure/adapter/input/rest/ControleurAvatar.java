package com.master2sir.entretien.infrastructure.adapter.input.rest;

import com.master2sir.entretien.application.service.ServiceModeAvatar;
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
@RequestMapping("/api/avatar")
@RequiredArgsConstructor
public class ControleurAvatar {

    private final ServiceModeAvatar serviceModeAvatar;

    @GetMapping("/entretiens/{interviewId}/question-suivante")
    public ResponseEntity<DTOQuestion> obtenirQuestionSuivante(@PathVariable UUID interviewId) {
        Question question = serviceModeAvatar.obtenirQuestionSuivante(interviewId);
        return ResponseEntity.ok(toDTO(question));
    }

    @GetMapping("/synthetiser")
    public ResponseEntity<byte[]> synthetiserQuestion(@RequestParam String texte) {
        byte[] audio = serviceModeAvatar.synthetiserQuestion(texte);
        return ResponseEntity.ok()
                .header("Content-Type", "audio/mpeg")
                .body(audio);
    }

    @PostMapping("/transcrire")
    public ResponseEntity<String> transcrireReponse(@RequestBody byte[] donneesAudio) {
        String transcription = serviceModeAvatar.transcrireReponse(donneesAudio);
        return ResponseEntity.ok(transcription);
    }

    @GetMapping("/generer-video")
    public ResponseEntity<String> genererVideoAvatar(@RequestParam String texte) {
        String urlVideo = serviceModeAvatar.genererVideoAvatar(texte);
        return ResponseEntity.ok(urlVideo);
    }

    @GetMapping("/generer-frame")
    public ResponseEntity<byte[]> genererFrameAvatar(@RequestParam String texte, @RequestParam String emotion) {
        byte[] frame = serviceModeAvatar.genererFrameAvatar(texte, emotion);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(frame);
    }

    @PostMapping("/entretiens/{interviewId}/reponses")
    public ResponseEntity<DTOReponse> soumettreReponse(@PathVariable UUID interviewId, @RequestBody CommandeSoumettreReponse commande) {
        commande.setIdEntretien(interviewId);
        Reponse reponse = serviceModeAvatar.soumettreReponse(commande);
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
