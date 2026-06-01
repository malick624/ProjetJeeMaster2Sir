package com.master2sir.entretien.application.service;

import com.master2sir.entretien.application.usecase.DTORapport;
import com.master2sir.entretien.domain.model.RapportEntretien;
import com.master2sir.entretien.domain.model.SessionEntretien;
import com.master2sir.entretien.domain.port.in.PortGenerationRapport;
import com.master2sir.entretien.domain.port.out.EntretienRepository;
import com.master2sir.entretien.domain.service.ServiceGenerateurFeedback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceApplicationRapport implements PortGenerationRapport {

    private final EntretienRepository entretienRepository;
    private final ServiceGenerateurFeedback serviceGenerateurFeedback;

    @Override
    public RapportEntretien genererRapport(UUID idEntretien) {
        SessionEntretien session = entretienRepository.findById(idEntretien)
                .orElseThrow(() -> new IllegalArgumentException("Entretien non trouvé"));

        return serviceGenerateurFeedback.genererFeedback(
                session.getReponses(),
                session.getDescriptionPoste()
        );
    }

    public DTORapport toDTO(RapportEntretien rapport) {
        return DTORapport.builder()
                .id(rapport.getId())
                .idSessionEntretien(rapport.getIdSessionEntretien())
                .resume(rapport.getResume())
                .pointsForts(rapport.getPointsForts())
                .axesAmelioration(rapport.getAxesAmelioration())
                .scoreGlobal(rapport.getScoreGlobal())
                .feedbackPersonnalise(rapport.getFeedbackPersonnalise())
                .sujetsRecommandes(rapport.getSujetsRecommandes())
                .dateGeneration(rapport.getDateGeneration())
                .build();
    }
}
