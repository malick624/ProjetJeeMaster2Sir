package com.master2sir.entretien.domain.model;

import com.master2sir.entretien.domain.model.ModeInteraction;
import com.master2sir.entretien.domain.model.EtatEntretien;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionEntretien {
    
    private UUID id;
    private UUID idCandidat;
    private DescriptionPoste descriptionPoste;
    private ProfilCandidat profilCandidat;
    private ModeInteraction modeInteraction;
    private EtatEntretien etat;
    private List<Question> questions;
    private List<Reponse> reponses;
    private RapportEntretien rapport;
    private LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int dureeMinutes;

    public void demarrer() {
        this.etat = EtatEntretien.EN_COURS;
        this.dateDebut = LocalDateTime.now();
    }

    public void terminer() {
        this.etat = EtatEntretien.TERMINE;
        this.dateFin = LocalDateTime.now();
        if (dateDebut != null) {
            this.dureeMinutes = (int) java.time.Duration.between(dateDebut, dateFin).toMinutes();
        }
    }

    public Question getQuestionActuelle() {
        if (reponses == null || reponses.isEmpty()) {
            return questions.get(0);
        }
        int indexActuel = reponses.size();
        if (indexActuel < questions.size()) {
            return questions.get(indexActuel);
        }
        return null;
    }

    public boolean estTermine() {
        return etat == EtatEntretien.TERMINE;
    }

	
}
