package com.master2sir.entretien.application.usecase;

import com.master2sir.entretien.domain.model.ModeInteraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequeteInitialiserEntretien {
    private String contenuDocument;
    private String descriptionPoste;
    private ModeInteraction mode;


}
