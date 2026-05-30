package com.master2sir.entretien.application.service;

import com.master2sir.entretien.application.usecase.CommandeSoumettreReponse;
import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.SessionEntretien;
import com.master2sir.entretien.domain.model.Question;
import com.master2sir.entretien.domain.port.out.PortSyntheseVocale;
import com.master2sir.entretien.domain.port.out.PortSyntheseParole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceModeVoix {

    private final ServiceApplicationEntretien serviceApplicationEntretien;
    private final PortSyntheseVocale portSyntheseVocale;
    private final PortSyntheseParole portSyntheseParole;

    public Question obtenirQuestionSuivante(UUID idEntretien) {
        return serviceApplicationEntretien.obtenirQuestionSuivante(idEntretien);
    }

    public byte[] synthetiserQuestion(String texteQuestion) {
        return portSyntheseParole.synthetiser(texteQuestion, "default");
    }

    public String transcrireReponse(byte[] donneesAudio) {
        return portSyntheseVocale.transcrire(donneesAudio, "fr");
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
