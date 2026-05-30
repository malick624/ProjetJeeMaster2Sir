package com.master2sir.entretien.infrastructure.adapter.output.persistence;

import com.master2sir.entretien.domain.model.SessionEntretien;
import com.master2sir.entretien.domain.port.out.EntretienRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JpaInterviewRepository implements EntretienRepository {

    private final SpringInterviewRepository springInterviewRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public SessionEntretien sauvegarder(SessionEntretien sessionEntretien) {
        InterviewEntity entity = toEntity(sessionEntretien);
        InterviewEntity savedEntity = springInterviewRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<SessionEntretien> findById(UUID id) {
        return springInterviewRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    @Transactional
    public void supprimer(UUID id) {
        springInterviewRepository.deleteById(id);
    }

    private InterviewEntity toEntity(SessionEntretien session) {
        try {
            return InterviewEntity.builder()
                    .id(session.getId())
                    .idCandidat(session.getIdCandidat())
                    .idDescriptionPoste(session.getDescriptionPoste() != null ? session.getDescriptionPoste().getId() : null)
                    .modeInteraction(session.getModeInteraction())
                    .etat(session.getEtat())
                    .questionsJson(session.getQuestions() != null ? objectMapper.writeValueAsString(session.getQuestions()) : null)
                    .reponsesJson(session.getReponses() != null ? objectMapper.writeValueAsString(session.getReponses()) : null)
                    .rapportJson(session.getRapport() != null ? objectMapper.writeValueAsString(session.getRapport()) : null)
                    .dateCreation(session.getDateCreation())
                    .dateDebut(session.getDateDebut())
                    .dateFin(session.getDateFin())
                    .dureeMinutes(session.getDureeMinutes())
                    .build();
        } catch (Exception e) {
            log.error("Erreur lors de la conversion de SessionEntretien vers entité", e);
            throw new RuntimeException("Échec de la conversion de SessionEntretien vers entité", e);
        }
    }

    private SessionEntretien toDomain(InterviewEntity entity) {
        try {
            return SessionEntretien.builder()
                    .id(entity.getId())
                    .idCandidat(entity.getIdCandidat())
                    .modeInteraction(entity.getModeInteraction())
                    .etat(entity.getEtat())
                    .questions(entity.getQuestionsJson() != null ? objectMapper.readValue(entity.getQuestionsJson(), new TypeReference<List<com.master2sir.entretien.domain.model.Question>>() {}) : null)
                    .reponses(entity.getReponsesJson() != null ? objectMapper.readValue(entity.getReponsesJson(), new TypeReference<List<com.master2sir.entretien.domain.model.Reponse>>() {}) : null)
                    .rapport(entity.getRapportJson() != null ? objectMapper.readValue(entity.getRapportJson(), com.master2sir.entretien.domain.model.RapportEntretien.class) : null)
                    .dateCreation(entity.getDateCreation())
                    .dateDebut(entity.getDateDebut())
                    .dateFin(entity.getDateFin())
                    .dureeMinutes(entity.getDureeMinutes())
                    .build();
        } catch (Exception e) {
            log.error("Erreur lors de la conversion de l'entité vers SessionEntretien", e);
            throw new RuntimeException("Échec de la conversion de l'entité vers SessionEntretien", e);
        }
    }
}
