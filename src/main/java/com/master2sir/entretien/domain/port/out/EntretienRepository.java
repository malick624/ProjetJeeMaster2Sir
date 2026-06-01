package com.master2sir.entretien.domain.port.out;

import com.master2sir.entretien.domain.model.SessionEntretien;

import java.util.Optional;
import java.util.UUID;

public interface EntretienRepository {
    SessionEntretien sauvegarder(SessionEntretien sessionEntretien);
    Optional<SessionEntretien> findById(UUID id);
    void supprimer(UUID id);
}
