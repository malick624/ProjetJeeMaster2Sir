package com.master2sir.entretien.application.service;

import com.master2sir.entretien.application.usecase.CommandeSoumettreReponse;
import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.SessionEntretien;
import com.master2sir.entretien.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceModeChat {

    private final ServiceApplicationEntretien serviceApplicationEntretien;

    public Question obtenirQuestionSuivante(UUID idEntretien) {
        return serviceApplicationEntretien.obtenirQuestionSuivante(idEntretien);
    }

    public Reponse soumettreReponse(CommandeSoumettreReponse commande) {
        return serviceApplicationEntretien.evaluerReponse(
                commande.getIdEntretien(),
                commande.getIdQuestion(),
                commande.getContenuReponse()
        );
    }

    public SessionEntretien obtenirSession(UUID idEntretien) {
        return serviceApplicationEntretien.obtenirEntretien(idEntretien);
    }
}
