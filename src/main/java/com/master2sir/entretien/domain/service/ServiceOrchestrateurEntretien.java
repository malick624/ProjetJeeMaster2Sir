package com.master2sir.entretien.domain.service;

import com.master2sir.entretien.domain.model.SessionEntretien;
import com.master2sir.entretien.domain.model.DescriptionPoste;
import com.master2sir.entretien.domain.model.Question;
import com.master2sir.entretien.domain.port.out.PortServiceChatIA;
import com.master2sir.entretien.domain.port.out.EntretienRepository;
import com.master2sir.entretien.domain.port.out.DescriptionPosteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ServiceOrchestrateurEntretien {

    private final PortServiceChatIA portServiceChatIA;
    private final EntretienRepository entretienRepository;
    private final DescriptionPosteRepository descriptionPosteRepository;

    public SessionEntretien initialiserEntretien(String contenuDescriptionPoste, int nombreQuestions) {
        DescriptionPoste descriptionPoste = portServiceChatIA.analyserDescriptionPoste(contenuDescriptionPoste);
        descriptionPosteRepository.sauvegarder(descriptionPoste);

        List<Question> questions = portServiceChatIA.genererQuestions(descriptionPoste, nombreQuestions);

        SessionEntretien session = SessionEntretien.builder()
                .descriptionPoste(descriptionPoste)
                .questions(questions)
                .build();

        return entretienRepository.sauvegarder(session);
    }

    public SessionEntretien demarrerSession(UUID idEntretien) {
        Optional<SessionEntretien> sessionOpt = entretienRepository.findById(idEntretien);
        if (sessionOpt.isEmpty()) {
            throw new IllegalArgumentException("Entretien non trouvé");
        }
        SessionEntretien session = sessionOpt.get();
        session.demarrer();
        return entretienRepository.sauvegarder(session);
    }

    public SessionEntretien terminerSession(UUID idEntretien) {
        Optional<SessionEntretien> sessionOpt = entretienRepository.findById(idEntretien);
        if (sessionOpt.isEmpty()) {
            throw new IllegalArgumentException("Entretien non trouvé");
        }
        SessionEntretien session = sessionOpt.get();
        session.terminer();
        return entretienRepository.sauvegarder(session);
    }
}
