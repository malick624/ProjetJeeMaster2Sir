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
public class ProfilCandidat {
    private UUID id;
    private String nom;
    private List<String> competences;
    private List<String> experience;
    private String education;
    private String resume;
    
    
}
