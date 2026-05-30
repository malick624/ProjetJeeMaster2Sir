package com.master2sir.entretien.domain.port.in;

import com.master2sir.entretien.domain.model.SessionEntretien;
import com.master2sir.entretien.domain.model.ModeInteraction;

import java.util.UUID;

public interface PortGestionEntretien {
    SessionEntretien creerEntretien(UUID idCandidat, String contenuDescriptionPoste, ModeInteraction mode);
    SessionEntretien demarrerEntretien(UUID idEntretien);
    SessionEntretien obtenirEntretien(UUID idEntretien); 
    SessionEntretien terminerEntretien(UUID idEntretien);
}
