package com.master2sir.entretien.infrastructure.adapter.output.persistence;

import com.master2sir.entretien.domain.model.DescriptionPoste;
import com.master2sir.entretien.domain.port.out.DescriptionPosteRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JpaJobDescriptionRepository implements DescriptionPosteRepository {

    private final SpringJobDescriptionRepository springJobDescriptionRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public DescriptionPoste sauvegarder(DescriptionPoste descriptionPoste) {
        JobDescriptionEntity entity = toEntity(descriptionPoste);
        JobDescriptionEntity savedEntity = springJobDescriptionRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<DescriptionPoste> findById(UUID id) {
        return springJobDescriptionRepository.findById(id)
                .map(this::toDomain);
    }

    private JobDescriptionEntity toEntity(DescriptionPoste descriptionPoste) {
        try {
            return JobDescriptionEntity.builder()
                    .id(descriptionPoste.getId())
                    .titre(descriptionPoste.getTitre())
                    .description(descriptionPoste.getDescription())
                    .competencesRequises(descriptionPoste.getCompetencesRequises() != null ? 
                            objectMapper.writeValueAsString(descriptionPoste.getCompetencesRequises()) : null)
                    .competencesPreferables(descriptionPoste.getCompetencesPreferables() != null ? 
                            objectMapper.writeValueAsString(descriptionPoste.getCompetencesPreferables()) : null)
                    .niveauExperience(descriptionPoste.getNiveauExperience())
                    .responsabilites(descriptionPoste.getResponsabilites() != null ? 
                            objectMapper.writeValueAsString(descriptionPoste.getResponsabilites()) : null)
                    .contenuBrut(descriptionPoste.getContenuBrut())
                    .build();
        } catch (Exception e) {
            log.error("Erreur lors de la conversion de DescriptionPoste vers entité", e);
            throw new RuntimeException("Échec de la conversion de DescriptionPoste vers entité", e);
        }
    }

    private DescriptionPoste toDomain(JobDescriptionEntity entity) {
        try {
            return DescriptionPoste.builder()
                    .id(entity.getId())
                    .titre(entity.getTitre())
                    .description(entity.getDescription())
                    .competencesRequises(entity.getCompetencesRequises() != null ? 
                            objectMapper.readValue(entity.getCompetencesRequises(), new TypeReference<List<String>>() {}) : null)
                    .competencesPreferables(entity.getCompetencesPreferables() != null ? 
                            objectMapper.readValue(entity.getCompetencesPreferables(), new TypeReference<List<String>>() {}) : null)
                    .niveauExperience(entity.getNiveauExperience())
                    .responsabilites(entity.getResponsabilites() != null ? 
                            objectMapper.readValue(entity.getResponsabilites(), new TypeReference<List<String>>() {}) : null)
                    .contenuBrut(entity.getContenuBrut())
                    .build();
        } catch (Exception e) {
            log.error("Erreur lors de la conversion de l'entité vers DescriptionPoste", e);
            throw new RuntimeException("Échec de la conversion de l'entité vers DescriptionPoste", e);
        }
    }
}
