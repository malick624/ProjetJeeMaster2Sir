package com.master2sir.entretien.application.service;

import com.master2sir.entretien.application.usecase.*;
import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.SessionEntretien;
import com.master2sir.entretien.domain.model.Question;
import com.master2sir.entretien.domain.model.ModeInteraction;
import com.master2sir.entretien.domain.port.in.*;
import com.master2sir.entretien.domain.port.out.EntretienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.master2sir.entretien.domain.service.ServiceOrchestrateurEntretien;
import com.master2sir.entretien.domain.service.ServiceAnalyseReponses;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceApplicationEntretien implements PortGestionEntretien, PortGenerationQuestions, PortEvaluationReponse {

    private final EntretienRepository entretienRepository = null;
    private final ServiceOrchestrateurEntretien serviceOrchestrateurEntretien;
    private final ServiceAnalyseReponses serviceAnalyseReponses;

    @Override
    @Transactional
    public SessionEntretien creerEntretien(UUID idCandidat, String contenuDescriptionPoste, ModeInteraction mode) {
        SessionEntretien session = serviceOrchestrateurEntretien.initialiserEntretien(contenuDescriptionPoste, 10);
        session.setIdCandidat(idCandidat);
        session.setModeInteraction(mode);
        return entretienRepository.sauvegarder(session);
    }

    @Override
    @Transactional
    public SessionEntretien demarrerEntretien(UUID idEntretien) {
        return serviceOrchestrateurEntretien.demarrerSession(idEntretien);
    }

    @Override
    public SessionEntretien obtenirEntretien(UUID idEntretien) {
        return entretienRepository.findById(idEntretien)
                .orElseThrow(() -> new IllegalArgumentException("Entretien non trouvé"));
    }

    @Override
    @Transactional
    public SessionEntretien terminerEntretien(UUID idEntretien) {
        return serviceOrchestrateurEntretien.terminerSession(idEntretien);
    }

    @Override
    public List<Question> genererQuestions(SessionEntretien sessionEntretien) {
        return sessionEntretien.getQuestions();
    }

    @Override
    public Question obtenirQuestionSuivante(UUID idEntretien) {
        SessionEntretien session = obtenirEntretien(idEntretien);
        return session.getQuestionActuelle();
    }

    @Override
    @Transactional
    public Reponse evaluerReponse(UUID idEntretien, UUID idQuestion, String contenuReponse) {
        SessionEntretien session = obtenirEntretien(idEntretien);
        Question question = session.getQuestions().stream()
                .filter(q -> q.getId().equals(idQuestion))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Question non trouvée"));

        Reponse reponse = serviceAnalyseReponses.analyserReponse(question, contenuReponse);
        reponse.setIdQuestion(idQuestion);

        if (session.getReponses() == null) {
            session.setReponses(List.of(reponse));
        } else {
            session.getReponses().add(reponse);
        }

        entretienRepository.sauvegarder(session);
        return reponse;
    }

    public DTOResponseEntretien toDTO(SessionEntretien session) {
        List<DTOQuestion> questionDTOs = session.getQuestions().stream()
                .map(this::questionToDTO)
                .collect(Collectors.toList());

        return DTOResponseEntretien.builder()
                .id(session.getId())
                .idCandidat(session.getIdCandidat())
                .titrePoste(session.getDescriptionPoste() != null ? session.getDescriptionPoste().getTitre() : null)
                .mode(session.getModeInteraction())
                .etat(session.getEtat())
                .questions(questionDTOs)
                .dateCreation(session.getDateCreation())
                .dateDebut(session.getDateDebut())
                .dateFin(session.getDateFin())
                .dureeMinutes(session.getDureeMinutes())
                .build();
    }

    private DTOQuestion questionToDTO(Question question) {
        return DTOQuestion.builder()
                .id(question.getId())
                .contenu(question.getContent())
                .categorie(question.getCategory())
                .competencesRequises(question.getRequiredSkills())
                .indexOrdre(question.getOrderIndex())
                .build();
    }
}
