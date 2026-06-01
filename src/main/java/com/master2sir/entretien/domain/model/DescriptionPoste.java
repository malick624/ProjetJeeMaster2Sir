package com.master2sir.entretien.domain.model;

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
public class DescriptionPoste {
    private UUID id;
    private String titre;
    private String description;
    private List<String> competencesRequises;
    private List<String> competencesPreferables;
    private String niveauExperience;
    private List<String> responsabilites;
    private String contenuBrut;
   
}
