package com.master2sir.entretien.domain.port.in;

import com.master2sir.entretien.domain.model.RapportEntretien;

import java.util.UUID;

public interface PortGenerationRapport {
    RapportEntretien genererRapport(UUID idEntretien);
}
