package com.master2sir.entretien.application.usecase;

import com.master2sir.entretien.domain.model.ModeInteraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeCreerEntretien {
    private UUID idCandidat;
    private String contenuDescriptionPoste;
    private ModeInteraction mode;
    private int nombreQuestions;
	
    
    
}
