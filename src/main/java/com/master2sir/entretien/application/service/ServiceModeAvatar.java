package com.master2sir.entretien.application.service;

import com.master2sir.entretien.application.usecase.CommandeSoumettreReponse;
import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.SessionEntretien;
import com.master2sir.entretien.domain.model.Question;
import com.master2sir.entretien.domain.port.out.PortGenerationAvatar;
import com.master2sir.entretien.domain.port.out.PortSyntheseVocale;
import com.master2sir.entretien.domain.port.out.PortSyntheseParole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceModeAvatar {

    private final ServiceApplicationEntretien serviceApplicationEntretien;
    private final PortSyntheseVocale portSyntheseVocale;
    private final PortSyntheseParole portSyntheseParole;
    private final PortGenerationAvatar portGenerationAvatar;

    public Question obtenirQuestionSuivante(UUID idEntretien) {
        return serviceApplicationEntretien.obtenirQuestionSuivante(idEntretien);
    }

    public byte[] synthetiserQuestion(String texteQuestion) {
        return portSyntheseParole.synthetiser(texteQuestion, "default");
    }

    public String transcrireReponse(byte[] donneesAudio) {
        return portSyntheseVocale.transcrire(donneesAudio, "fr");
    }

    public String genererVideoAvatar(String texte) {
        return portGenerationAvatar.genererUrlVideo(texte, "default-avatar");
    }

    public byte[] genererFrameAvatar(String texte, String emotion) {
        return portGenerationAvatar.genererFrameVideo(texte, "default-avatar", emotion);
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
