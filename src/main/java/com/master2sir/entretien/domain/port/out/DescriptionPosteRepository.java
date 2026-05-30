package com.master2sir.entretien.domain.port.out;

import com.master2sir.entretien.domain.model.DescriptionPoste;

import java.util.Optional;
import java.util.UUID;

public interface DescriptionPosteRepository {
    DescriptionPoste sauvegarder(DescriptionPoste descriptionPoste);
    Optional<DescriptionPoste> findById(UUID id);
}
