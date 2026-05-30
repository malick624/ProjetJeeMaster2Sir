package com.master2sir.entretien.application.usecase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTOQuestion {
    private UUID id;
    private String contenu;
    private String categorie;
    private List<String> competencesRequises;
    private int indexOrdre;
	
    
}
