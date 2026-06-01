package com.master2sir.entretien.domain.service;

import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.RapportEntretien;
import com.master2sir.entretien.domain.model.DescriptionPoste;
import com.master2sir.entretien.domain.port.out.PortServiceChatIA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceGenerateurFeedback {

    private final PortServiceChatIA portServiceChatIA;

    public RapportEntretien genererFeedback(List<Reponse> reponses, DescriptionPoste descriptionPoste) {
        return portServiceChatIA.genererRapport(reponses, descriptionPoste);
    }
}
