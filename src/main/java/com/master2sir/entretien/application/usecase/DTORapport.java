package com.master2sir.entretien.application.usecase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTORapport {
    private UUID id;
    private UUID idSessionEntretien;
    private String resume;
    private List<String> pointsForts;
    private List<String> axesAmelioration;
    private int scoreGlobal;
    private String feedbackPersonnalise;
    private List<String> sujetsRecommandes;
    private LocalDateTime dateGeneration;
}
