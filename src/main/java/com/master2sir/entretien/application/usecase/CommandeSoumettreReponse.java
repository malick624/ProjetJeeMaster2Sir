package com.master2sir.entretien.application.usecase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeSoumettreReponse {
    private UUID idEntretien;
    private UUID idQuestion;
    private String contenuReponse;
	
    
    
}
