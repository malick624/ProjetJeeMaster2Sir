package com.master2sir.entretien.domain.port.in;

import com.master2sir.entretien.domain.model.Reponse;

import java.util.UUID;

public interface PortEvaluationReponse {
    Reponse evaluerReponse(UUID idEntretien, UUID idQuestion, String contenuReponse);
}
