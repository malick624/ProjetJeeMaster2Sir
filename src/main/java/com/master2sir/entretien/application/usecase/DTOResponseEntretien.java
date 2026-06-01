package com.master2sir.entretien.application.usecase;

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
public class DTOResponseEntretien {
    private UUID id;
    private UUID idCandidat;
    private String titrePoste;
    private ModeInteraction mode;
    private EtatEntretien etat;
    private List<DTOQuestion> questions;
    private LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int dureeMinutes;
}
